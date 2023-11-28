// ============================================================================
//
// 版权 (c) 2006-2015, Talend Inc.
//
// 此源代码已由 Talend Open Studio for Data Integration 自动生成
// / 根据 Apache 许可证 2.0 版 (“许可证”) 授予许可;
// 只有在满足许可证要求的前提下您才可以使用本文件。
// 要获取获取许可证的副本，请访问
 // http://www.apache.org/licenses/LICENSE-2.0
//
 // 除非适用法律要求或书面同意，
//否则根据许可证分发的软件须按“原样”提供，
//且不附带任何明示或暗示的担保或条件。
// 有关许可证下具体的权限和
//限制规定，请参阅许可证。
//
// ============================================================================
package routines;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

import routines.system.RandomUtils;

public class Numeric {

    private static final java.util.Map<String, Integer> seq_Hash = new ConcurrentHashMap<>();

    /**
     * return an incremented numeric id
     *
     * {talendTypes} int | Integer
     *
     * {Category} Numeric
     *
     * {param} string("s1") sequence identifier
     *
     * {param} int(1) start value
     *
     * {param} int(1) step
     *
     * {example} sequence("s1", 1, 1) # 1, 2, 3, ...
     *
     * {example} sequence("s2", 100, -2) # 100, 98, 96, ...
     *
     */
    public static Integer sequence(String seqName, int startValue, int step) {
        return seq_Hash.compute(seqName,
                (String k,  Integer v) -> v == null ? startValue : v + step);
    }

    /**
     * create a sequence if not exists and put a new startValue
     *
     * {Category} Numeric
     *
     * {param} string("s1") sequence identifier
     *
     * {param} int(1) start value
     *
     * {example} sequence("s1", 1)
     *
     */

    public static void resetSequence(String seqName, int startValue) {
        seq_Hash.put(seqName, startValue);
    }

    /**
     * remove a sequence
     *
     * {Category} Numeric
     *
     * {param} string("s1") sequence identifier
     *
     * {example} sequence("s1")
     *
     */

    public static void removeSequence(String seqName) {
        seq_Hash.remove(seqName);
    }

    /**
     * return a random int between min and max
     *
     * {Category} Numeric
     *
     * {talendTypes} int | Integer
     *
     * {param} int(0) min value
     *
     * {param} int(100) max value
     *
     * {example} random(3, 10) # 7, 4, 8, ...
     *
     * {example} random(0, 100) # 93, 12, 83, ...
     *
     * @throws RuntimeException in case when max value is smaller than min value
     */
    public static Integer random(Integer min, Integer max) {
        if (max < min) {
            throw new RuntimeException("Max value should be bigger than min value");
        }
        return ((Long) Math.round(min - 0.5 + (RandomUtils.random() * (max - min + 1)))).intValue();
    }

    /**
     * return numbers using an implied decimal format.
     *
     * {Category} Numeric
     *
     * {talendTypes} float | Float
     *
     * {param} String("9V99") format: float pointing format.
     *
     * {param} String("123") toConvert: read this value.
     *
     * {example} convertImpliedDecimalFormat("9V99", "123") result: 1.23 ...
     *
     */
    public static Float convertImpliedDecimalFormat(String format, String toConvert) {
        BigDecimal decimal = Numeric.convertString2BigDecimal(format,toConvert);
        return new Float(decimal.doubleValue());
    }

    public static BigDecimal convertString2BigDecimal(String format, String toConvert) {
        long decimalPlace = 1;
        int indexOf = format.indexOf('V');
        if (indexOf > -1) {
            boolean isV = false;
            for (int i = 0; i < format.length(); i++) {
                char charAt = format.charAt(i);
                if (charAt == '9' && isV) {
                    decimalPlace = 10 * decimalPlace;
                } else if (charAt == 'V') {
                    isV = true;
                }
            }
        }
        BigDecimal decimal = new BigDecimal(toConvert);
        decimal = decimal.divide(new BigDecimal(decimalPlace));
        return decimal;
    }
}
