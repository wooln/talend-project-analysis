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

public class DataOperation {

    /**
     * Converts a numeric value to its ASCII character string equivalent.
     *
     * {talendTypes} char | Character
     *
     * {Category} DataOperation
     *
     * {param} int(1) i: numeric value
     *
     * {example} CHAR(1):int
     *
     */
    public static char CHAR(int i) {
        return Character.forDigit(i, 10);
    }

    /**
     * Converts a decimal integer into its hexadecimal equivalent.
     *
     * {talendTypes} String
     *
     * {Category} DataOperation
     *
     * {param} int(1) i:decimal integer
     *
     * {example} DTX(1)
     *
     */
    public static String DTX(int i) {
        return Integer.toHexString(i);
    }

    /**
     * Rounds a number of type Double to a number of type Long with the precision specified in the PRECISION statement.
     *
     * {talendTypes} long | Long
     *
     * {Category} DataOperation
     *
     * {param} double (0.0) d:double number
     *
     * {example} FIX(3.14)
     *
     */
    public static long FIX(double d) {
        return Math.round(d);
    }

    /**
     * Converts a hexadecimal string into its decimal equivalent.
     *
     * {talendTypes} int | Integer
     *
     * {Category} DataOperation
     *
     * {param} string ("0") text: hexadecimal string
     *
     * {example} XTD(\"1\")
     *
     */
    public static int XTD(String text) {
        return Integer.valueOf(text, 16);
    }
}
