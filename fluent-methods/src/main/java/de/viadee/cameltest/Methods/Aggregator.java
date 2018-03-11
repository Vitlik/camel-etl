package de.viadee.cameltest.Methods;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * dient dazu...
 * 
 * Annahmen und Voraussetzungen sind...
 * 
 * @param <T>
 */

public class Aggregator<T> implements AggregatorTargetToBeSet, AggregatorGroupByStart, AggregatorGroupByStarted,
        AggregatorAttributeSet, AggregatorOperationSet, AggregatorAttributeTargetSet {

    private List<T> sourceList;

    private Class<T> targetClass;

    private List<String> groupAttr = new ArrayList<String>();

    private HashMap<String, Entry<String, String>> operList = new HashMap<String, Entry<String, String>>();

    private String lastAttr;

    public AggregatorTargetToBeSet setSource(List<T> sourceList) {
        this.sourceList = sourceList;
        return this;
    }

    @Override
    public AggregatorGroupByStart setTarget(String target) {
        try {
            targetClass = (Class<T>) Class.forName(target);
            return this;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AggregatorGroupByStarted groupBy(String attributeName) {
        groupAttr.add(attributeName);
        return this;
    }

    @Override
    public AggregatorAttributeSet setAttribute(String attributeName) {
        operList.put(attributeName, null);
        lastAttr = attributeName;
        return this;
    }

    @Override
    public AggregatorOperationSet setOperation(String countMaxMinSumOther) {
        Map.Entry<String, String> newEntry = new AbstractMap.SimpleEntry<String, String>(countMaxMinSumOther, null);
        operList.put(lastAttr, newEntry);
        return this;
    }

    @Override
    public AggregatorAttributeTargetSet setAttributeTarget(String attributeTargetName) {
        operList.get(lastAttr).setValue(attributeTargetName);
        return this;
    }

    @Override
    public Object run() {

        try {

            // a HashMap of lists to store the groups for each operation based on its attributes for grouping
            HashMap<String, List<T>> groupList = new HashMap<String, List<T>>();

            Class<?> sClass = sourceList.get(0).getClass();

            createGroupList(groupList, sClass);

            List<Object> targetList = new ArrayList<Object>();

            executeAggregation(groupList, sClass, targetList);

            return targetList;

        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException |

                SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param groupList
     * @param sClass
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void createGroupList(HashMap<String, List<T>> groupList, Class<?> sClass)
            throws NoSuchFieldException, IllegalAccessException {

        Field sourceField;

        // divide sourceList into groups and store to groupList
        for (Object elem : sourceList) {
            String groupName = "";
            for (String attr : groupAttr) {
                sourceField = sClass.getDeclaredField(attr);

                groupName += sourceField.get(elem);

            }

            if (!groupList.containsKey(groupName)) {
                List<T> newGroup = new ArrayList<T>();
                newGroup.add((T) elem);
                groupList.put(groupName, newGroup);
            } else {
                groupList.get(groupName).add((T) elem);
            }
        }
    }

    private void executeAggregation(HashMap<String, List<T>> groupList, Class<?> sClass, List<Object> targetList)
            throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Field sourceField, targetField;
        // in each group perform the defined operations, if set
        for (Entry<String, List<T>> entry : groupList.entrySet()) {

            Object targetObject = targetClass.newInstance();

            // write group fields to target object
            for (String attr : groupAttr) {
                sourceField = sClass.getDeclaredField(attr);
                targetField = targetClass.getDeclaredField(attr);

                // store name of field for this column.
                // First Object is sufficient as all objects should have the same group names
                Object cellName = sourceField.get(entry.getValue().get(0));
                targetField.set(targetObject, cellName);
            }

            // execute Operations
            for (Entry<String, Entry<String, String>> oper : operList.entrySet()) {

                int objectCounter = 0;

                String op = oper.getValue().getKey();
                op.toLowerCase();

                targetField = targetClass.getDeclaredField(oper.getValue().getValue());

                // if the operation is "count" we do not need to go through all elements.
                // Simply take the size of the group
                if (op == "count") {
                    targetField.set(targetObject, entry.getValue().size());
                    break;
                }

                Double resultNum = null;
                String resultString = null;
                Boolean resultIsNumber = null;

                if (op.matches("max|min|sum|avg")) {
                    resultIsNumber = true;
                } else {
                    resultIsNumber = false;
                }

                sourceField = sClass.getDeclaredField(oper.getKey());

                // iterate through group members
                for (Object listElem : entry.getValue()) {

                    objectCounter++;

                    if (objectCounter == 1) {
                        if (resultIsNumber) {
                            resultNum = (Double) sourceField.get(listElem);
                            continue;
                        } else {
                            if (op == "first") {
                                resultString = (String) sourceField.get(listElem);
                            } else if (op == "last") {
                                // only other operation left is "last"
                                // Retrieve last element and skip member iteration
                                resultString = (String) sourceField
                                        .get(entry.getValue().get(entry.getValue().size() - 1));
                            } else {
                                // unknown operation
                                throw new RuntimeException("Operator -" + op + "- unknown. "
                                        + "Please use max, min, sum, avg, first or last");
                            }
                            break;
                        }
                    }

                    switch (op) {
                        case "max":
                            if (resultNum < (double) sourceField.get(listElem)) {
                                resultNum = (double) sourceField.get(listElem);
                            }
                            break;
                        case "min":
                            if (resultNum > (double) sourceField.get(listElem)) {
                                resultNum = (double) sourceField.get(listElem);
                            }
                            break;
                        case "sum":
                            resultNum += (double) sourceField.get(listElem);
                            break;
                        case "avg":
                            resultNum = (resultNum * (objectCounter - 1) + (double) sourceField.get(listElem))
                                    / objectCounter;
                            break;
                        default:
                            // impossible
                            throw new RuntimeException("Should be impossible to get here.");
                    }

                }

                if (resultIsNumber) {
                    targetField.set(targetObject, resultNum);
                } else {
                    targetField.set(targetObject, resultString);
                }
            }

            targetList.add(targetObject);
        }
    }
}
