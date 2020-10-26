package com.sly.medicineshop.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库存运算工具类
 *
 * @author SLY
 * @date 2020/9/30
 */
public class StorageConvertUtils {

    /**
     * 库存相加
     *
     * @param count1 加数1
     * @param count2 加数2
     * @param unit   库存单位
     * @return java.lang.String
     * @author SLY
     * @date 2020/9/30
     */
    public static String addition(String count1, String count2, StorageUnit unit) {
        List<StorageUnit> unitList = new ArrayList<>();
        unitList = getUnitList(unitList, unit);

        List<String> units = unitList.stream().map(StorageUnit::getName).collect(Collectors.toList());
        for (StorageUnit storageUnit : unitList) {
            String[] count1s = count1.split(",");
            for (String s : count1s) {
                String[] split = s.split(":");
                if (!units.contains(split[0])) {
                    throw new RuntimeException("非法的单位：" + split[0]);
                }
                if (storageUnit.getName().equals(split[0])) {
                    storageUnit.setNumber(storageUnit.getNumber().add(new BigDecimal(split[1])));
                }
            }

            String[] count2s = count2.split(",");
            for (String s : count2s) {
                String[] split = s.split(":");
                if (!units.contains(split[0])) {
                    throw new RuntimeException("非法的单位：" + split[0]);
                }
                if (storageUnit.getName().equals(split[0])) {
                    storageUnit.setNumber(storageUnit.getNumber().add(new BigDecimal(split[1])));
                }
            }
        }
        String countNew = "";
        List<StorageUnit> storageList = unitList.stream().filter(e -> e.getNumber().compareTo(new BigDecimal("0")) > 0).collect(Collectors.toList());
        for (int i = 0; i < storageList.size(); i++) {
            if (i == storageList.size() - 1) {
                countNew += storageList.get(i).getName() + ":" + storageList.get(i).getNumber();
            } else {
                countNew += storageList.get(i).getName() + ":" + storageList.get(i).getNumber() + ",";
            }
        }
        return countNew;
    }

    /**
     * 库存相减
     *
     * @param count1 被减数
     * @param count2 减数
     * @param unit   库存单位
     * @return java.lang.String
     * @author SLY
     * @date 2020/9/30
     */
    public static String subtraction(String count1, String count2, StorageUnit unit) {
        BigDecimal countMiniUnit1 = convertToMinUnit(count1, unit);
        BigDecimal countMiniUnit2 = convertToMinUnit(count2, unit);
        if (countMiniUnit1.compareTo(countMiniUnit2) < 0) {
            throw new RuntimeException("库存不足！");
        } else {
            List<StorageUnit> unitCountList1 = getUnitCountList(count1, unit);
            List<StorageUnit> unitCountList2 = getUnitCountList(count2, unit);
            Collections.reverse(unitCountList1);
            Collections.reverse(unitCountList2);
            for (StorageUnit storageUnit : unitCountList2) {
                subtractSingle(unitCountList1, storageUnit);
            }
            Collections.reverse(unitCountList1);
            return convertToString(unitCountList1);

        }
    }

    /**
     * 转换为最小单位
     *
     * @param count 数量
     * @param unit  库存单位
     * @return java.math.BigDecimal
     * @author SLY
     * @date 2020/9/30
     */
    public static BigDecimal convertToMinUnit(String count, StorageUnit unit) {
        BigDecimal totalCount = new BigDecimal("0");
        if (count == null || "".equals(count)) {
            return totalCount;
        }
        String[] split = count.split(",");
        for (String str : split) {
            String[] split1 = str.split(":");
            BigDecimal total = new BigDecimal("0");
            total = convert(total, split1, unit);
            totalCount = totalCount.add(total);
        }
        return totalCount;
    }

    /**
     * 转换为特定单位包含拆零
     *
     * @param count      数量
     * @param unit       库存单位
     * @param targetUnit 目标单位
     * @return java.math.BigDecimal
     * @author SLY
     * @date 2020/9/30
     */
    public static BigDecimal convertToTargetUnit(String count, StorageUnit unit, String targetUnit) {
        BigDecimal miniTotalCount = convertToMinUnit(count, unit);
        BigDecimal convertCount = miniUnitConvertToTargetUnitCount(unit, targetUnit);
        return miniTotalCount.divide(convertCount, BigDecimal.ROUND_DOWN);
    }

    /**
     * 转换为特定单位去除拆零
     *
     * @param count      数量
     * @param unit       库存单位
     * @param targetUnit 目标单位
     * @return java.math.BigDecimal
     * @author SLY
     * @date 2020/9/30
     */
    public static BigDecimal convertToTargetUnitWithOutDismounted(String count, StorageUnit unit, String targetUnit) {
        BigDecimal totalCount = new BigDecimal("0");
        String[] split = count.split(",");
        int index = 0;
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split(":");
            if (split1[0].equals(targetUnit)) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            if (!split[0].split(":")[0].equals(targetUnit)) {
                throw new RuntimeException("非法的目标单位：" + targetUnit);
            }
        }
        for (int i = 0; i < split.length; i++) {
            if (i > index) {
                break;
            }
            String[] split1 = split[i].split(":");
            BigDecimal total = new BigDecimal("0");
            total = convert(total, split1, unit, targetUnit);
            totalCount = totalCount.add(total);
        }

        return totalCount;
    }

    /**
     * 转换为指定单位
     */
    private static BigDecimal convert(BigDecimal total, String[] split, StorageUnit unit, String targetUnit) {
        if (unit.getName().equals(targetUnit)) {
            total = new BigDecimal(split[1]);
        } else if (unit.getName().equals(split[0])) {
            if (unit.getSubUnit() != null) {
                total = convertHelp(new BigDecimal(split[1]), unit.getSubUnit(), targetUnit);
            } else {
                total = new BigDecimal(split[1]);
            }
        } else if (unit.getSubUnit() != null) {
            total = convert(total, split, unit.getSubUnit(), targetUnit);
        } else {
            throw new RuntimeException("非法的单位：" + split[0]);
        }
        return total;
    }

    /**
     * 求每一种转为指定单位后的总数
     */
    private static BigDecimal convertHelp(BigDecimal count, StorageUnit unit, String targetUnit) {
        count = count.multiply(unit.getCarryNumber());
        if (unit.getName().equals(targetUnit)) {
            return count;
        } else if (unit.getSubUnit() != null) {
            return convertHelp(count, unit.getSubUnit(), targetUnit);
        } else {
            return count;
        }
    }

    /**
     * 转换为最小单位
     */
    private static BigDecimal convert(BigDecimal total, String[] split, StorageUnit unit) {
        if (unit.getName().equals(split[0])) {
            if (unit.getSubUnit() != null) {
                total = convertHelp(new BigDecimal(split[1]), unit.getSubUnit());
            } else {
                total = new BigDecimal(split[1]);
            }
        } else if (unit.getSubUnit() != null) {
            total = convert(total, split, unit.getSubUnit());
        } else {
            throw new RuntimeException("非法的单位：" + split[0]);

        }
        return total;
    }

    /**
     * 求每一种转为最小单位后的总数
     */
    private static BigDecimal convertHelp(BigDecimal count, StorageUnit unit) {
        count = count.multiply(unit.getCarryNumber());
        if (unit.getSubUnit() != null) {
            return convertHelp(count, unit.getSubUnit());
        } else {
            return count;
        }
    }

    /**
     * 获取最小单位转换到目标单位需要的数量
     */
    private static BigDecimal miniUnitConvertToTargetUnitCount(StorageUnit unit, String targetUnit) {
        List<StorageUnit> unitList = new ArrayList<>();
        unitList = getUnitList(unitList, unit);
        boolean isTarget = false;
        BigDecimal count = new BigDecimal("0");
        for (StorageUnit storageUnit : unitList) {
            if (storageUnit.getName().equals(targetUnit)) {
                isTarget = true;
                count = new BigDecimal("1");
            }
            if (isTarget && !storageUnit.getName().equals(targetUnit)) {
                count = count.multiply(storageUnit.getCarryNumber());
            }
        }
        return count;
    }

    private static List<StorageUnit> getUnitList(List<StorageUnit> unitList, StorageUnit unit) {
        unitList.add(new StorageUnit(unit.getName(), unit.getCarryNumber()));
        if (unit.getSubUnit() != null) {
            getUnitList(unitList, unit.getSubUnit());
        }
        return unitList;
    }

    private static List<StorageUnit> getUnitCountList(String count, StorageUnit unit) {
        List<StorageUnit> unitList = new ArrayList<>();
        unitList = getUnitList(unitList, unit);
        if (count == null || "".equals(count)) {
            for (StorageUnit storageUnit : unitList) {
                storageUnit.setNumber(new BigDecimal("0"));
            }
        } else {
            String[] split = count.split(",");
            for (String s : split) {
                String[] split1 = s.split(":");
                for (StorageUnit storageUnit : unitList) {
                    if (split1[0].equals(storageUnit.getName())) {
                        storageUnit.setNumber(new BigDecimal(split1[1]));
                    }
                }
            }
        }
        return unitList;
    }

    private static void subtractSingle(List<StorageUnit> unitList, StorageUnit unit) {
        for (StorageUnit storageUnit : unitList) {
            if (storageUnit.getName().equals(unit.getName())) {
                if (storageUnit.getNumber().compareTo(unit.getNumber()) >= 0) {
                    storageUnit.setNumber(storageUnit.getNumber().subtract(unit.getNumber()));
                    unit.setNumber(new BigDecimal("0"));
                } else {
                    unit.setNumber(unit.getNumber().subtract(storageUnit.getNumber()));
                    storageUnit.setNumber(new BigDecimal("0"));
                    borrowingNumber(unitList, unit);
                    subtractSingle(unitList, unit);
                }
            }
        }
    }

    private static void borrowingNumber(List<StorageUnit> unitList, StorageUnit unit) {
        for (int i = 0; i < unitList.size(); i++) {
            if (unit.getName().equals(unitList.get(i).getName())) {
                if ((i + 1) >= unitList.size()) {
                    throw new RuntimeException("库存不足！");
                }
                if (unitList.get(i + 1).getNumber().compareTo(new BigDecimal("0")) <= 0) {
                    borrowingNumber(unitList, unitList.get(i + 1));
                }
                unitList.get(i).setNumber(unitList.get(i).getCarryNumber());
                unitList.get(i + 1).setNumber(unitList.get(i + 1).getNumber().subtract(new BigDecimal("1")));
            }
        }
    }

    private static String convertToString(List<StorageUnit> unitList) {
        String count = "";
        for (StorageUnit storageUnit : unitList) {
            if (storageUnit.getNumber().compareTo(new BigDecimal("0")) > 0) {
                count += storageUnit.getName() + ":" + storageUnit.getNumber() + ",";
            }
        }
        if (count.endsWith(",")) {
            count = count.substring(0, count.length() - 1);
        }
        return count;
    }
}
