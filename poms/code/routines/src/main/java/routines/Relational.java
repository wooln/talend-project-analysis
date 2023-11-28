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

public class Relational {

    /**
     * Indicates when a variable is the null value.
     *
     * {talendTypes} boolean | Boolean
     *
     * {Category} Relational
     *
     * {param} Object(null)
     *
     * {example} ISNULL(null)
     *
     *
     */
    public static boolean ISNULL(Object variable) {
        return variable == null;
    }

    /**
     * Returns the complement of the logical value of an expression.
     *
     * {talendTypes} boolean | Boolean
     *
     * {Category} Relational
     *
     * {param} boolean(true)
     *
     * {example} NOT(false)
     */
    public static boolean NOT(boolean expression) {
        return !expression;
    }
    
    /**
     * Indicates when a variable is the null value.
     * 
     * @param value :the object need to judge.
     * 
     * @return 1 if value == null, 0 if not.
     * 
     * {Category} Relational
     * 
     * {param} Object(null)
     * 
     * {example} ISNULL(null)
     */
	public static int isNull(Object value) {
		if (value == null) {
			return 1;
		}
		return 0;

	}
}
