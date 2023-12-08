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

package export_talend7_context.export_job_relation_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: export_job_relation Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class export_job_relation implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (projectDir != null) {

				this.setProperty("projectDir", projectDir.toString());

			}

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

		public String projectDir;

		public String getProjectDir() {
			return this.projectDir;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "export_job_relation";
	private final String projectName = "EXPORT_TALEND7_CONTEXT";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					export_job_relation.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(export_job_relation.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputJSON_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputJSON_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[0];
		static byte[] commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[0];

		public String id;

		public String getId() {
			return this.id;
		}

		public Float version;

		public Float getVersion() {
			return this.version;
		}

		public String itemType;

		public String getItemType() {
			return this.itemType;
		}

		public String relatedItem;

		public String getRelatedItem() {
			return this.relatedItem;
		}

		public String relatedVersion;

		public String getRelatedVersion() {
			return this.relatedVersion;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation.length) {
					if (length < 1024 && commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation.length == 0) {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[1024];
					} else {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation, 0, length);
				strReturn = new String(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation.length) {
					if (length < 1024 && commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation.length == 0) {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[1024];
					} else {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation, 0, length);
				strReturn = new String(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job_relation) {

				try {

					int length = 0;

					this.id = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.version = null;
					} else {
						this.version = dis.readFloat();
					}

					this.itemType = readString(dis);

					this.relatedItem = readString(dis);

					this.relatedVersion = readString(dis);

					this.type = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job_relation) {

				try {

					int length = 0;

					this.id = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.version = null;
					} else {
						this.version = dis.readFloat();
					}

					this.itemType = readString(dis);

					this.relatedItem = readString(dis);

					this.relatedVersion = readString(dis);

					this.type = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// Float

				if (this.version == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.version);
				}

				// String

				writeString(this.itemType, dos);

				// String

				writeString(this.relatedItem, dos);

				// String

				writeString(this.relatedVersion, dos);

				// String

				writeString(this.type, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// Float

				if (this.version == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.version);
				}

				// String

				writeString(this.itemType, dos);

				// String

				writeString(this.relatedItem, dos);

				// String

				writeString(this.relatedVersion, dos);

				// String

				writeString(this.type, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",version=" + String.valueOf(version));
			sb.append(",itemType=" + itemType);
			sb.append(",relatedItem=" + relatedItem);
			sb.append(",relatedVersion=" + relatedVersion);
			sb.append(",type=" + type);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[0];
		static byte[] commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[0];

		public String id;

		public String getId() {
			return this.id;
		}

		public Float version;

		public Float getVersion() {
			return this.version;
		}

		public String itemType;

		public String getItemType() {
			return this.itemType;
		}

		public String relatedItem;

		public String getRelatedItem() {
			return this.relatedItem;
		}

		public String relatedVersion;

		public String getRelatedVersion() {
			return this.relatedVersion;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation.length) {
					if (length < 1024 && commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation.length == 0) {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[1024];
					} else {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation, 0, length);
				strReturn = new String(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation.length) {
					if (length < 1024 && commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation.length == 0) {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[1024];
					} else {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation, 0, length);
				strReturn = new String(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job_relation, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job_relation) {

				try {

					int length = 0;

					this.id = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.version = null;
					} else {
						this.version = dis.readFloat();
					}

					this.itemType = readString(dis);

					this.relatedItem = readString(dis);

					this.relatedVersion = readString(dis);

					this.type = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job_relation) {

				try {

					int length = 0;

					this.id = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.version = null;
					} else {
						this.version = dis.readFloat();
					}

					this.itemType = readString(dis);

					this.relatedItem = readString(dis);

					this.relatedVersion = readString(dis);

					this.type = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// Float

				if (this.version == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.version);
				}

				// String

				writeString(this.itemType, dos);

				// String

				writeString(this.relatedItem, dos);

				// String

				writeString(this.relatedVersion, dos);

				// String

				writeString(this.type, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// Float

				if (this.version == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.version);
				}

				// String

				writeString(this.itemType, dos);

				// String

				writeString(this.relatedItem, dos);

				// String

				writeString(this.relatedVersion, dos);

				// String

				writeString(this.type, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",version=" + String.valueOf(version));
			sb.append(",itemType=" + itemType);
			sb.append(",relatedItem=" + relatedItem);
			sb.append(",relatedVersion=" + relatedVersion);
			sb.append(",type=" + type);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputJSON_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputJSON_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();

				/**
				 * [tFileOutputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileOutputExcel_1", false);
				start_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileOutputExcel_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tFileOutputExcel_1 = 0;

				int columnIndex_tFileOutputExcel_1 = 0;
				boolean headerIsInserted_tFileOutputExcel_1 = false;

				int nb_line_tFileOutputExcel_1 = 0;

				String fileName_tFileOutputExcel_1 = context.projectDir + "relationship.xlsx";
				java.io.File file_tFileOutputExcel_1 = new java.io.File(fileName_tFileOutputExcel_1);
				boolean isFileGenerated_tFileOutputExcel_1 = true;
				if (file_tFileOutputExcel_1.exists()) {
					isFileGenerated_tFileOutputExcel_1 = false;
				}
//create directory only if not exists
				java.io.File parentFile_tFileOutputExcel_1 = file_tFileOutputExcel_1.getParentFile();
				if (parentFile_tFileOutputExcel_1 != null && !parentFile_tFileOutputExcel_1.exists()) {

					parentFile_tFileOutputExcel_1.mkdirs();

				}

				jxl.write.WritableWorkbook writeableWorkbook_tFileOutputExcel_1 = null;
				jxl.write.WritableSheet writableSheet_tFileOutputExcel_1 = null;

				jxl.WorkbookSettings workbookSettings_tFileOutputExcel_1 = new jxl.WorkbookSettings();
				workbookSettings_tFileOutputExcel_1.setEncoding("ISO-8859-15");
				if (file_tFileOutputExcel_1.exists()) {
					jxl.Workbook workbook_tFileOutputExcel_1 = jxl.Workbook.getWorkbook(file_tFileOutputExcel_1,
							workbookSettings_tFileOutputExcel_1);
					workbookSettings_tFileOutputExcel_1.setWriteAccess(null);
					writeableWorkbook_tFileOutputExcel_1 = new jxl.write.biff.WritableWorkbookImpl(
							new java.io.BufferedOutputStream(
									new java.io.FileOutputStream(file_tFileOutputExcel_1, false)),
							workbook_tFileOutputExcel_1, true, workbookSettings_tFileOutputExcel_1);
				} else {
					writeableWorkbook_tFileOutputExcel_1 = new jxl.write.biff.WritableWorkbookImpl(
							new java.io.BufferedOutputStream(new java.io.FileOutputStream(fileName_tFileOutputExcel_1)),
							true, workbookSettings_tFileOutputExcel_1);
				}

				writableSheet_tFileOutputExcel_1 = writeableWorkbook_tFileOutputExcel_1.getSheet("Sheet1");
				if (writableSheet_tFileOutputExcel_1 == null) {
					writableSheet_tFileOutputExcel_1 = writeableWorkbook_tFileOutputExcel_1.createSheet("Sheet1",
							writeableWorkbook_tFileOutputExcel_1.getNumberOfSheets());
				}

				// modif start
				int startRowNum_tFileOutputExcel_1 = writableSheet_tFileOutputExcel_1.getRows();
				// modif end

				int[] fitWidth_tFileOutputExcel_1 = new int[6];
				for (int i_tFileOutputExcel_1 = 0; i_tFileOutputExcel_1 < 6; i_tFileOutputExcel_1++) {
					int fitCellViewSize_tFileOutputExcel_1 = writableSheet_tFileOutputExcel_1
							.getColumnView(i_tFileOutputExcel_1).getSize();
					fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1] = fitCellViewSize_tFileOutputExcel_1 / 256;
					if (fitCellViewSize_tFileOutputExcel_1 % 256 != 0) {
						fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1] += 1;
					}
				}

				if (startRowNum_tFileOutputExcel_1 == 0) {
					// modif end
					// modif start
					writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(0, nb_line_tFileOutputExcel_1, "id"));
					// modif end
					fitWidth_tFileOutputExcel_1[0] = fitWidth_tFileOutputExcel_1[0] > 2 ? fitWidth_tFileOutputExcel_1[0]
							: 2;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(1, nb_line_tFileOutputExcel_1, "version"));
					// modif end
					fitWidth_tFileOutputExcel_1[1] = fitWidth_tFileOutputExcel_1[1] > 7 ? fitWidth_tFileOutputExcel_1[1]
							: 7;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(2, nb_line_tFileOutputExcel_1, "itemType"));
					// modif end
					fitWidth_tFileOutputExcel_1[2] = fitWidth_tFileOutputExcel_1[2] > 8 ? fitWidth_tFileOutputExcel_1[2]
							: 8;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(3, nb_line_tFileOutputExcel_1, "relatedItem"));
					// modif end
					fitWidth_tFileOutputExcel_1[3] = fitWidth_tFileOutputExcel_1[3] > 11
							? fitWidth_tFileOutputExcel_1[3]
							: 11;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(4, nb_line_tFileOutputExcel_1, "relatedVersion"));
					// modif end
					fitWidth_tFileOutputExcel_1[4] = fitWidth_tFileOutputExcel_1[4] > 14
							? fitWidth_tFileOutputExcel_1[4]
							: 14;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(5, nb_line_tFileOutputExcel_1, "type"));
					// modif end
					fitWidth_tFileOutputExcel_1[5] = fitWidth_tFileOutputExcel_1[5] > 4 ? fitWidth_tFileOutputExcel_1[5]
							: 4;
					nb_line_tFileOutputExcel_1++;
					headerIsInserted_tFileOutputExcel_1 = true;
				}

				/**
				 * [tFileOutputExcel_1 begin ] stop
				 */

				/**
				 * [tFilterRow_1 begin ] start
				 */

				ok_Hash.put("tFilterRow_1", false);
				start_Hash.put("tFilterRow_1", System.currentTimeMillis());

				currentComponent = "tFilterRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tFilterRow_1 = 0;

				int nb_line_tFilterRow_1 = 0;
				int nb_line_ok_tFilterRow_1 = 0;
				int nb_line_reject_tFilterRow_1 = 0;

				class Operator_tFilterRow_1 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_1(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_1 begin ] stop
				 */

				/**
				 * [tFileInputJSON_1 begin ] start
				 */

				ok_Hash.put("tFileInputJSON_1", false);
				start_Hash.put("tFileInputJSON_1", System.currentTimeMillis());

				currentComponent = "tFileInputJSON_1";

				int tos_count_tFileInputJSON_1 = 0;

				int nb_line_tFileInputJSON_1 = 0;

				class XML_API_tFileInputJSON_1 {
					public boolean isDefNull(org.dom4j.Node node) throws javax.xml.transform.TransformerException {
						if (node != null && node instanceof org.dom4j.Element) {
							org.dom4j.Attribute attri = ((org.dom4j.Element) node).attribute("nil");
							if (attri != null && ("true").equals(attri.getText())) {
								return true;
							}
						}
						return false;
					}

					public boolean isMissing(org.dom4j.Node node) throws javax.xml.transform.TransformerException {
						return node == null ? true : false;
					}

					public boolean isEmpty(org.dom4j.Node node) throws javax.xml.transform.TransformerException {
						if (node != null) {
							return node.getText().length() == 0;
						}
						return false;
					}
				}

				class ConvertJSONString_tFileInputJSON_1 {
					final static int Brace = 0; // {
					final static int Bracket = 1; // [
					private int barceType = -1;
					private String originalJsonString = "";
					private String originalLoopString = "";
					private String jsonString4XML = null;
					private String loopString4XML = null;
					private String additionRoot = null;

					public void barceType() {

						for (int c = 0; c < originalJsonString.length(); ++c) {
							if (originalJsonString.charAt(c) == '{') {
								barceType = Brace;
								break;
							} else if (originalJsonString.charAt(c) == '[') {
								barceType = Bracket;
								break;
							}
						}
					}

					public void setJsonString(String originalJsonString) {
						this.originalJsonString = originalJsonString;
					}

					public void setLoopString(String originalLoopString) {
						this.originalLoopString = originalLoopString;
					}

					public String getJsonString4XML() {
						return jsonString4XML;
					}

					public String getLoopString4XML() {
						if (loopString4XML.length() > 1 && loopString4XML.endsWith("/")) {
							loopString4XML = loopString4XML.substring(0, loopString4XML.length() - 1);
						}
						return loopString4XML;
					}

					public void setAdditionRoot(String additionRoot) {
						this.additionRoot = additionRoot;
					}

					public String getAdditionRoot() {
						return additionRoot;
					}

					public void generate() {
						barceType();
						jsonString4XML = originalJsonString;
						loopString4XML = originalLoopString;
						if (Brace == barceType) {
							if (isNeedAddRoot(originalJsonString)) {
								jsonString4XML = "{ \"root\": " + originalJsonString + " }";
								loopString4XML = "root" + originalLoopString;
								setAdditionRoot("root");
							}
						} else if (Bracket == barceType) {
							jsonString4XML = "{ \"root\" : { \"object\": " + originalJsonString + " } }";
							loopString4XML = "root/object" + originalLoopString;
							setAdditionRoot("object");
						}
					}

					public boolean isNeedAddRoot(String originalJsonString) {
						boolean isNeedAddRoot = false;
						net.sf.json.JSONObject jso = net.sf.json.JSONObject.fromObject(originalJsonString);
						String jsonKey = "";
						Object firstObject = null;
						if (jso.names().size() == 1) {
							jsonKey = jso.names().get(0).toString();
							firstObject = jso.get(jsonKey);
						}
						if (jso.size() > 1 || (firstObject != null && firstObject instanceof net.sf.json.JSONArray
								&& ((net.sf.json.JSONArray) firstObject).size() > 1)) {
							isNeedAddRoot = true;
						}
						return isNeedAddRoot;
					}

				}

				ConvertJSONString_tFileInputJSON_1 cjs_tFileInputJSON_1 = new ConvertJSONString_tFileInputJSON_1();

				de.odysseus.staxon.json.JsonXMLConfig config_tFileInputJSON_1 = new de.odysseus.staxon.json.JsonXMLConfigBuilder()
						.multiplePI(false).build();
				de.odysseus.staxon.json.JsonXMLInputFactory jsonXMLInputFactory_tFileInputJSON_1 = new de.odysseus.staxon.json.JsonXMLInputFactory(
						config_tFileInputJSON_1);
				javax.xml.stream.XMLOutputFactory xmlOutputFactory_tFileInputJSON_1 = javax.xml.stream.XMLOutputFactory
						.newInstance();
				boolean isGetWholeJson_tFileInputJSON_1 = false;

				org.dom4j.io.SAXReader reader_tFileInputJSON_1 = new org.dom4j.io.SAXReader();
				Object filenameOrStream_tFileInputJSON_1 = null;
				try {
					filenameOrStream_tFileInputJSON_1 = context.projectDir + ".settings/relationship.index";
				} catch (java.lang.Exception e_tFileInputJSON_1) {
					globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());

					System.err.println(e_tFileInputJSON_1.getMessage());
				}

				boolean isValidFile_tFileInputJSON_1 = true;
				org.dom4j.Document doc_tFileInputJSON_1 = null;
				java.io.BufferedReader br_tFileInputJSON_1 = null;
				java.lang.StringBuilder jsonBuffer_tFileInputJSON_1 = new java.lang.StringBuilder("");
				String jsonStr_tFileInputJSON_1 = null;
				String xmlStr_tFileInputJSON_1 = "";
				String line_tFileInputJSON_1 = null;

				String loopQuery_tFileInputJSON_1 = "/relatedItems";
				java.io.ByteArrayInputStream bais_tFileInputJSON_1 = null;
				java.io.ByteArrayOutputStream baos_tFileInputJSON_1 = new java.io.ByteArrayOutputStream();

				try {
					if (filenameOrStream_tFileInputJSON_1 instanceof java.io.InputStream) {
						br_tFileInputJSON_1 = new java.io.BufferedReader(new java.io.InputStreamReader(
								(java.io.InputStream) filenameOrStream_tFileInputJSON_1, "UTF-8"));
					} else {
						br_tFileInputJSON_1 = new java.io.BufferedReader(new java.io.InputStreamReader(
								new java.io.FileInputStream(String.valueOf(filenameOrStream_tFileInputJSON_1)),
								"UTF-8"));
					}
					while ((line_tFileInputJSON_1 = br_tFileInputJSON_1.readLine()) != null) {
						jsonBuffer_tFileInputJSON_1.append(line_tFileInputJSON_1);
					}
					jsonStr_tFileInputJSON_1 = jsonBuffer_tFileInputJSON_1.toString();
					cjs_tFileInputJSON_1.setJsonString(jsonStr_tFileInputJSON_1);
					cjs_tFileInputJSON_1.setLoopString(loopQuery_tFileInputJSON_1);
					cjs_tFileInputJSON_1.generate();
					jsonStr_tFileInputJSON_1 = cjs_tFileInputJSON_1.getJsonString4XML();
					loopQuery_tFileInputJSON_1 = cjs_tFileInputJSON_1.getLoopString4XML();
					bais_tFileInputJSON_1 = new ByteArrayInputStream(jsonStr_tFileInputJSON_1.getBytes("UTF-8"));
					javax.xml.stream.XMLEventReader xmlEventReader_tFileInputJSON_1 = jsonXMLInputFactory_tFileInputJSON_1
							.createXMLEventReader(bais_tFileInputJSON_1);
					javax.xml.stream.XMLEventWriter xmLEventWriter_tFileInputJSON_1 = xmlOutputFactory_tFileInputJSON_1
							.createXMLEventWriter(baos_tFileInputJSON_1, "UTF-8");
					xmLEventWriter_tFileInputJSON_1.add(xmlEventReader_tFileInputJSON_1);
					// convert json string to xml
					xmlStr_tFileInputJSON_1 = baos_tFileInputJSON_1.toString();
					xmLEventWriter_tFileInputJSON_1.close();
					xmlEventReader_tFileInputJSON_1.close();
					doc_tFileInputJSON_1 = reader_tFileInputJSON_1
							.read(new java.io.StringReader(xmlStr_tFileInputJSON_1));
				} catch (java.lang.Exception e_tFileInputJSON_1) {
					globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());

					System.err.println(e_tFileInputJSON_1.getMessage());
					isValidFile_tFileInputJSON_1 = false;
				} finally {
					if (br_tFileInputJSON_1 != null) {
						br_tFileInputJSON_1.close();
					}
					baos_tFileInputJSON_1.close();
					if (bais_tFileInputJSON_1 != null) {
						bais_tFileInputJSON_1.close();
					}
				}
				if (isValidFile_tFileInputJSON_1) {

					org.dom4j.XPath x_tFileInputJSON_1 = doc_tFileInputJSON_1.createXPath(loopQuery_tFileInputJSON_1);
					java.util.HashMap<String, String> xmlNameSpaceMap_tFileInputJSON_1 = new java.util.HashMap<String, String>();
					x_tFileInputJSON_1 = doc_tFileInputJSON_1.createXPath(loopQuery_tFileInputJSON_1);
					x_tFileInputJSON_1.setNamespaceURIs(xmlNameSpaceMap_tFileInputJSON_1);
					java.util.List<org.dom4j.Node> nodeList_tFileInputJSON_1 = (java.util.List<org.dom4j.Node>) x_tFileInputJSON_1
							.selectNodes(doc_tFileInputJSON_1);
					XML_API_tFileInputJSON_1 xml_api_tFileInputJSON_1 = new XML_API_tFileInputJSON_1();
					String str_tFileInputJSON_1 = "";
					org.dom4j.Node node_tFileInputJSON_1 = null;

					// init all mapping xpaths
					org.dom4j.XPath xTmp0_tFileInputJSON_1 = org.dom4j.DocumentHelper.createXPath("../baseItem/id");
					xTmp0_tFileInputJSON_1.setNamespaceURIs(xmlNameSpaceMap_tFileInputJSON_1);
					org.dom4j.XPath xTmp1_tFileInputJSON_1 = org.dom4j.DocumentHelper
							.createXPath("../baseItem/version");
					xTmp1_tFileInputJSON_1.setNamespaceURIs(xmlNameSpaceMap_tFileInputJSON_1);
					org.dom4j.XPath xTmp2_tFileInputJSON_1 = org.dom4j.DocumentHelper.createXPath("../baseItem/type");
					xTmp2_tFileInputJSON_1.setNamespaceURIs(xmlNameSpaceMap_tFileInputJSON_1);
					org.dom4j.XPath xTmp3_tFileInputJSON_1 = org.dom4j.DocumentHelper.createXPath("id");
					xTmp3_tFileInputJSON_1.setNamespaceURIs(xmlNameSpaceMap_tFileInputJSON_1);
					org.dom4j.XPath xTmp4_tFileInputJSON_1 = org.dom4j.DocumentHelper.createXPath("version");
					xTmp4_tFileInputJSON_1.setNamespaceURIs(xmlNameSpaceMap_tFileInputJSON_1);
					org.dom4j.XPath xTmp5_tFileInputJSON_1 = org.dom4j.DocumentHelper.createXPath("type");
					xTmp5_tFileInputJSON_1.setNamespaceURIs(xmlNameSpaceMap_tFileInputJSON_1);

					for (org.dom4j.Node temp_tFileInputJSON_1 : nodeList_tFileInputJSON_1) {
						nb_line_tFileInputJSON_1++;
						row1 = null;
						boolean whetherReject_tFileInputJSON_1 = false;
						row1 = new row1Struct();
						try {
							Object obj0_tFileInputJSON_1 = xTmp0_tFileInputJSON_1.evaluate(temp_tFileInputJSON_1);
							if (obj0_tFileInputJSON_1 == null) {
								node_tFileInputJSON_1 = null;
								str_tFileInputJSON_1 = "";
							} else if (obj0_tFileInputJSON_1 instanceof org.dom4j.Node) {
								node_tFileInputJSON_1 = (org.dom4j.Node) obj0_tFileInputJSON_1;
								str_tFileInputJSON_1 = org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
										org.jaxen.dom4j.DocumentNavigator.getInstance());
							} else if (obj0_tFileInputJSON_1 instanceof String
									|| obj0_tFileInputJSON_1 instanceof Number) {
								node_tFileInputJSON_1 = temp_tFileInputJSON_1;
								str_tFileInputJSON_1 = String.valueOf(obj0_tFileInputJSON_1);
							} else if (obj0_tFileInputJSON_1 instanceof java.util.List) {
								java.util.List<org.dom4j.Node> nodes_tFileInputJSON_1 = (java.util.List<org.dom4j.Node>) obj0_tFileInputJSON_1;
								node_tFileInputJSON_1 = nodes_tFileInputJSON_1.size() > 0
										? nodes_tFileInputJSON_1.get(0)
										: null;
								str_tFileInputJSON_1 = node_tFileInputJSON_1 == null ? ""
										: org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
												org.jaxen.dom4j.DocumentNavigator.getInstance());
							}
							if (xml_api_tFileInputJSON_1.isDefNull(node_tFileInputJSON_1)) {
								row1.id = null;
							} else if (xml_api_tFileInputJSON_1.isEmpty(node_tFileInputJSON_1)) {
								row1.id = "";
							} else if (xml_api_tFileInputJSON_1.isMissing(node_tFileInputJSON_1)) {
								row1.id = null;
							} else {
								row1.id = str_tFileInputJSON_1;
							}
							Object obj1_tFileInputJSON_1 = xTmp1_tFileInputJSON_1.evaluate(temp_tFileInputJSON_1);
							if (obj1_tFileInputJSON_1 == null) {
								node_tFileInputJSON_1 = null;
								str_tFileInputJSON_1 = "";
							} else if (obj1_tFileInputJSON_1 instanceof org.dom4j.Node) {
								node_tFileInputJSON_1 = (org.dom4j.Node) obj1_tFileInputJSON_1;
								str_tFileInputJSON_1 = org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
										org.jaxen.dom4j.DocumentNavigator.getInstance());
							} else if (obj1_tFileInputJSON_1 instanceof String
									|| obj1_tFileInputJSON_1 instanceof Number) {
								node_tFileInputJSON_1 = temp_tFileInputJSON_1;
								str_tFileInputJSON_1 = String.valueOf(obj1_tFileInputJSON_1);
							} else if (obj1_tFileInputJSON_1 instanceof java.util.List) {
								java.util.List<org.dom4j.Node> nodes_tFileInputJSON_1 = (java.util.List<org.dom4j.Node>) obj1_tFileInputJSON_1;
								node_tFileInputJSON_1 = nodes_tFileInputJSON_1.size() > 0
										? nodes_tFileInputJSON_1.get(0)
										: null;
								str_tFileInputJSON_1 = node_tFileInputJSON_1 == null ? ""
										: org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
												org.jaxen.dom4j.DocumentNavigator.getInstance());
							}
							if (xml_api_tFileInputJSON_1.isDefNull(node_tFileInputJSON_1)) {
								row1.version = null;
							} else if (xml_api_tFileInputJSON_1.isEmpty(node_tFileInputJSON_1)
									|| xml_api_tFileInputJSON_1.isMissing(node_tFileInputJSON_1)) {
								row1.version = null;
							} else {
								row1.version = ParserUtils.parseTo_Float(str_tFileInputJSON_1);
							}
							Object obj2_tFileInputJSON_1 = xTmp2_tFileInputJSON_1.evaluate(temp_tFileInputJSON_1);
							if (obj2_tFileInputJSON_1 == null) {
								node_tFileInputJSON_1 = null;
								str_tFileInputJSON_1 = "";
							} else if (obj2_tFileInputJSON_1 instanceof org.dom4j.Node) {
								node_tFileInputJSON_1 = (org.dom4j.Node) obj2_tFileInputJSON_1;
								str_tFileInputJSON_1 = org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
										org.jaxen.dom4j.DocumentNavigator.getInstance());
							} else if (obj2_tFileInputJSON_1 instanceof String
									|| obj2_tFileInputJSON_1 instanceof Number) {
								node_tFileInputJSON_1 = temp_tFileInputJSON_1;
								str_tFileInputJSON_1 = String.valueOf(obj2_tFileInputJSON_1);
							} else if (obj2_tFileInputJSON_1 instanceof java.util.List) {
								java.util.List<org.dom4j.Node> nodes_tFileInputJSON_1 = (java.util.List<org.dom4j.Node>) obj2_tFileInputJSON_1;
								node_tFileInputJSON_1 = nodes_tFileInputJSON_1.size() > 0
										? nodes_tFileInputJSON_1.get(0)
										: null;
								str_tFileInputJSON_1 = node_tFileInputJSON_1 == null ? ""
										: org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
												org.jaxen.dom4j.DocumentNavigator.getInstance());
							}
							if (xml_api_tFileInputJSON_1.isDefNull(node_tFileInputJSON_1)) {
								row1.itemType = null;
							} else if (xml_api_tFileInputJSON_1.isEmpty(node_tFileInputJSON_1)) {
								row1.itemType = "";
							} else if (xml_api_tFileInputJSON_1.isMissing(node_tFileInputJSON_1)) {
								row1.itemType = null;
							} else {
								row1.itemType = str_tFileInputJSON_1;
							}
							Object obj3_tFileInputJSON_1 = xTmp3_tFileInputJSON_1.evaluate(temp_tFileInputJSON_1);
							if (obj3_tFileInputJSON_1 == null) {
								node_tFileInputJSON_1 = null;
								str_tFileInputJSON_1 = "";
							} else if (obj3_tFileInputJSON_1 instanceof org.dom4j.Node) {
								node_tFileInputJSON_1 = (org.dom4j.Node) obj3_tFileInputJSON_1;
								str_tFileInputJSON_1 = org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
										org.jaxen.dom4j.DocumentNavigator.getInstance());
							} else if (obj3_tFileInputJSON_1 instanceof String
									|| obj3_tFileInputJSON_1 instanceof Number) {
								node_tFileInputJSON_1 = temp_tFileInputJSON_1;
								str_tFileInputJSON_1 = String.valueOf(obj3_tFileInputJSON_1);
							} else if (obj3_tFileInputJSON_1 instanceof java.util.List) {
								java.util.List<org.dom4j.Node> nodes_tFileInputJSON_1 = (java.util.List<org.dom4j.Node>) obj3_tFileInputJSON_1;
								node_tFileInputJSON_1 = nodes_tFileInputJSON_1.size() > 0
										? nodes_tFileInputJSON_1.get(0)
										: null;
								str_tFileInputJSON_1 = node_tFileInputJSON_1 == null ? ""
										: org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
												org.jaxen.dom4j.DocumentNavigator.getInstance());
							}
							if (xml_api_tFileInputJSON_1.isDefNull(node_tFileInputJSON_1)) {
								row1.relatedItem = null;
							} else if (xml_api_tFileInputJSON_1.isEmpty(node_tFileInputJSON_1)) {
								row1.relatedItem = "";
							} else if (xml_api_tFileInputJSON_1.isMissing(node_tFileInputJSON_1)) {
								row1.relatedItem = null;
							} else {
								row1.relatedItem = str_tFileInputJSON_1;
							}
							Object obj4_tFileInputJSON_1 = xTmp4_tFileInputJSON_1.evaluate(temp_tFileInputJSON_1);
							if (obj4_tFileInputJSON_1 == null) {
								node_tFileInputJSON_1 = null;
								str_tFileInputJSON_1 = "";
							} else if (obj4_tFileInputJSON_1 instanceof org.dom4j.Node) {
								node_tFileInputJSON_1 = (org.dom4j.Node) obj4_tFileInputJSON_1;
								str_tFileInputJSON_1 = org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
										org.jaxen.dom4j.DocumentNavigator.getInstance());
							} else if (obj4_tFileInputJSON_1 instanceof String
									|| obj4_tFileInputJSON_1 instanceof Number) {
								node_tFileInputJSON_1 = temp_tFileInputJSON_1;
								str_tFileInputJSON_1 = String.valueOf(obj4_tFileInputJSON_1);
							} else if (obj4_tFileInputJSON_1 instanceof java.util.List) {
								java.util.List<org.dom4j.Node> nodes_tFileInputJSON_1 = (java.util.List<org.dom4j.Node>) obj4_tFileInputJSON_1;
								node_tFileInputJSON_1 = nodes_tFileInputJSON_1.size() > 0
										? nodes_tFileInputJSON_1.get(0)
										: null;
								str_tFileInputJSON_1 = node_tFileInputJSON_1 == null ? ""
										: org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
												org.jaxen.dom4j.DocumentNavigator.getInstance());
							}
							if (xml_api_tFileInputJSON_1.isDefNull(node_tFileInputJSON_1)) {
								row1.relatedVersion = null;
							} else if (xml_api_tFileInputJSON_1.isEmpty(node_tFileInputJSON_1)) {
								row1.relatedVersion = "";
							} else if (xml_api_tFileInputJSON_1.isMissing(node_tFileInputJSON_1)) {
								row1.relatedVersion = null;
							} else {
								row1.relatedVersion = str_tFileInputJSON_1;
							}
							Object obj5_tFileInputJSON_1 = xTmp5_tFileInputJSON_1.evaluate(temp_tFileInputJSON_1);
							if (obj5_tFileInputJSON_1 == null) {
								node_tFileInputJSON_1 = null;
								str_tFileInputJSON_1 = "";
							} else if (obj5_tFileInputJSON_1 instanceof org.dom4j.Node) {
								node_tFileInputJSON_1 = (org.dom4j.Node) obj5_tFileInputJSON_1;
								str_tFileInputJSON_1 = org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
										org.jaxen.dom4j.DocumentNavigator.getInstance());
							} else if (obj5_tFileInputJSON_1 instanceof String
									|| obj5_tFileInputJSON_1 instanceof Number) {
								node_tFileInputJSON_1 = temp_tFileInputJSON_1;
								str_tFileInputJSON_1 = String.valueOf(obj5_tFileInputJSON_1);
							} else if (obj5_tFileInputJSON_1 instanceof java.util.List) {
								java.util.List<org.dom4j.Node> nodes_tFileInputJSON_1 = (java.util.List<org.dom4j.Node>) obj5_tFileInputJSON_1;
								node_tFileInputJSON_1 = nodes_tFileInputJSON_1.size() > 0
										? nodes_tFileInputJSON_1.get(0)
										: null;
								str_tFileInputJSON_1 = node_tFileInputJSON_1 == null ? ""
										: org.jaxen.function.StringFunction.evaluate(node_tFileInputJSON_1,
												org.jaxen.dom4j.DocumentNavigator.getInstance());
							}
							if (xml_api_tFileInputJSON_1.isDefNull(node_tFileInputJSON_1)) {
								row1.type = null;
							} else if (xml_api_tFileInputJSON_1.isEmpty(node_tFileInputJSON_1)) {
								row1.type = "";
							} else if (xml_api_tFileInputJSON_1.isMissing(node_tFileInputJSON_1)) {
								row1.type = null;
							} else {
								row1.type = str_tFileInputJSON_1;
							}

						} catch (java.lang.Exception e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							whetherReject_tFileInputJSON_1 = true;
							System.err.println(e_tFileInputJSON_1.getMessage());
							row1 = null;
						}

						/**
						 * [tFileInputJSON_1 begin ] stop
						 */

						/**
						 * [tFileInputJSON_1 main ] start
						 */

						currentComponent = "tFileInputJSON_1";

						tos_count_tFileInputJSON_1++;

						/**
						 * [tFileInputJSON_1 main ] stop
						 */

						/**
						 * [tFileInputJSON_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputJSON_1";

						/**
						 * [tFileInputJSON_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tFilterRow_1 main ] start
							 */

							currentComponent = "tFilterRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							row2 = null;
							Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
							ope_tFilterRow_1.matches(
									(row1.itemType == null ? false : row1.itemType.compareTo("job") == 0),
									"itemType.compareTo(\"job\") == 0 failed");
							ope_tFilterRow_1.matches((row1.type == null ? false : row1.type.compareTo("job") == 0),
									"type.compareTo(\"job\") == 0 failed");

							if (ope_tFilterRow_1.getMatchFlag()) {
								if (row2 == null) {
									row2 = new row2Struct();
								}
								row2.id = row1.id;
								row2.version = row1.version;
								row2.itemType = row1.itemType;
								row2.relatedItem = row1.relatedItem;
								row2.relatedVersion = row1.relatedVersion;
								row2.type = row1.type;
								nb_line_ok_tFilterRow_1++;
							} else {
								nb_line_reject_tFilterRow_1++;
							}

							nb_line_tFilterRow_1++;

							tos_count_tFilterRow_1++;

							/**
							 * [tFilterRow_1 main ] stop
							 */

							/**
							 * [tFilterRow_1 process_data_begin ] start
							 */

							currentComponent = "tFilterRow_1";

							/**
							 * [tFilterRow_1 process_data_begin ] stop
							 */
// Start of branch "row2"
							if (row2 != null) {

								/**
								 * [tFileOutputExcel_1 main ] start
								 */

								currentComponent = "tFileOutputExcel_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

								if (row2.id != null) {

//modif start

									columnIndex_tFileOutputExcel_1 = 0;

									jxl.write.WritableCell cell_0_tFileOutputExcel_1 = new jxl.write.Label(
											columnIndex_tFileOutputExcel_1,
											startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
											row2.id);
//modif start					
									// If we keep the cell format from the existing cell in sheet

//modif ends							
									writableSheet_tFileOutputExcel_1.addCell(cell_0_tFileOutputExcel_1);
									int currentWith_0_tFileOutputExcel_1 = cell_0_tFileOutputExcel_1.getContents()
											.trim().length();
									fitWidth_tFileOutputExcel_1[0] = fitWidth_tFileOutputExcel_1[0] > currentWith_0_tFileOutputExcel_1
											? fitWidth_tFileOutputExcel_1[0]
											: currentWith_0_tFileOutputExcel_1 + 2;
								}

								if (row2.version != null) {

//modif start

									columnIndex_tFileOutputExcel_1 = 1;

									jxl.write.WritableCell cell_1_tFileOutputExcel_1 = new jxl.write.Number(
											columnIndex_tFileOutputExcel_1,
											startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
											row2.version);
//modif start					
									// If we keep the cell format from the existing cell in sheet

//modif ends							
									writableSheet_tFileOutputExcel_1.addCell(cell_1_tFileOutputExcel_1);
									int currentWith_1_tFileOutputExcel_1 = String
											.valueOf(((jxl.write.Number) cell_1_tFileOutputExcel_1).getValue()).trim()
											.length();
									currentWith_1_tFileOutputExcel_1 = currentWith_1_tFileOutputExcel_1 > 10 ? 10
											: currentWith_1_tFileOutputExcel_1;
									fitWidth_tFileOutputExcel_1[1] = fitWidth_tFileOutputExcel_1[1] > currentWith_1_tFileOutputExcel_1
											? fitWidth_tFileOutputExcel_1[1]
											: currentWith_1_tFileOutputExcel_1 + 2;
								}

								if (row2.itemType != null) {

//modif start

									columnIndex_tFileOutputExcel_1 = 2;

									jxl.write.WritableCell cell_2_tFileOutputExcel_1 = new jxl.write.Label(
											columnIndex_tFileOutputExcel_1,
											startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
											row2.itemType);
//modif start					
									// If we keep the cell format from the existing cell in sheet

//modif ends							
									writableSheet_tFileOutputExcel_1.addCell(cell_2_tFileOutputExcel_1);
									int currentWith_2_tFileOutputExcel_1 = cell_2_tFileOutputExcel_1.getContents()
											.trim().length();
									fitWidth_tFileOutputExcel_1[2] = fitWidth_tFileOutputExcel_1[2] > currentWith_2_tFileOutputExcel_1
											? fitWidth_tFileOutputExcel_1[2]
											: currentWith_2_tFileOutputExcel_1 + 2;
								}

								if (row2.relatedItem != null) {

//modif start

									columnIndex_tFileOutputExcel_1 = 3;

									jxl.write.WritableCell cell_3_tFileOutputExcel_1 = new jxl.write.Label(
											columnIndex_tFileOutputExcel_1,
											startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
											row2.relatedItem);
//modif start					
									// If we keep the cell format from the existing cell in sheet

//modif ends							
									writableSheet_tFileOutputExcel_1.addCell(cell_3_tFileOutputExcel_1);
									int currentWith_3_tFileOutputExcel_1 = cell_3_tFileOutputExcel_1.getContents()
											.trim().length();
									fitWidth_tFileOutputExcel_1[3] = fitWidth_tFileOutputExcel_1[3] > currentWith_3_tFileOutputExcel_1
											? fitWidth_tFileOutputExcel_1[3]
											: currentWith_3_tFileOutputExcel_1 + 2;
								}

								if (row2.relatedVersion != null) {

//modif start

									columnIndex_tFileOutputExcel_1 = 4;

									jxl.write.WritableCell cell_4_tFileOutputExcel_1 = new jxl.write.Label(
											columnIndex_tFileOutputExcel_1,
											startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
											row2.relatedVersion);
//modif start					
									// If we keep the cell format from the existing cell in sheet

//modif ends							
									writableSheet_tFileOutputExcel_1.addCell(cell_4_tFileOutputExcel_1);
									int currentWith_4_tFileOutputExcel_1 = cell_4_tFileOutputExcel_1.getContents()
											.trim().length();
									fitWidth_tFileOutputExcel_1[4] = fitWidth_tFileOutputExcel_1[4] > currentWith_4_tFileOutputExcel_1
											? fitWidth_tFileOutputExcel_1[4]
											: currentWith_4_tFileOutputExcel_1 + 2;
								}

								if (row2.type != null) {

//modif start

									columnIndex_tFileOutputExcel_1 = 5;

									jxl.write.WritableCell cell_5_tFileOutputExcel_1 = new jxl.write.Label(
											columnIndex_tFileOutputExcel_1,
											startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
											row2.type);
//modif start					
									// If we keep the cell format from the existing cell in sheet

//modif ends							
									writableSheet_tFileOutputExcel_1.addCell(cell_5_tFileOutputExcel_1);
									int currentWith_5_tFileOutputExcel_1 = cell_5_tFileOutputExcel_1.getContents()
											.trim().length();
									fitWidth_tFileOutputExcel_1[5] = fitWidth_tFileOutputExcel_1[5] > currentWith_5_tFileOutputExcel_1
											? fitWidth_tFileOutputExcel_1[5]
											: currentWith_5_tFileOutputExcel_1 + 2;
								}

								nb_line_tFileOutputExcel_1++;

								tos_count_tFileOutputExcel_1++;

								/**
								 * [tFileOutputExcel_1 main ] stop
								 */

								/**
								 * [tFileOutputExcel_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputExcel_1";

								/**
								 * [tFileOutputExcel_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputExcel_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputExcel_1";

								/**
								 * [tFileOutputExcel_1 process_data_end ] stop
								 */

							} // End of branch "row2"

							/**
							 * [tFilterRow_1 process_data_end ] start
							 */

							currentComponent = "tFilterRow_1";

							/**
							 * [tFilterRow_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputJSON_1 process_data_end ] start
						 */

						currentComponent = "tFileInputJSON_1";

						/**
						 * [tFileInputJSON_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputJSON_1 end ] start
						 */

						currentComponent = "tFileInputJSON_1";

					}
				}
				globalMap.put("tFileInputJSON_1_NB_LINE", nb_line_tFileInputJSON_1);

				ok_Hash.put("tFileInputJSON_1", true);
				end_Hash.put("tFileInputJSON_1", System.currentTimeMillis());

				/**
				 * [tFileInputJSON_1 end ] stop
				 */

				/**
				 * [tFilterRow_1 end ] start
				 */

				currentComponent = "tFilterRow_1";

				globalMap.put("tFilterRow_1_NB_LINE", nb_line_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_OK", nb_line_ok_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_REJECT", nb_line_reject_tFilterRow_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tFilterRow_1", true);
				end_Hash.put("tFilterRow_1", System.currentTimeMillis());

				/**
				 * [tFilterRow_1 end ] stop
				 */

				/**
				 * [tFileOutputExcel_1 end ] start
				 */

				currentComponent = "tFileOutputExcel_1";

				writeableWorkbook_tFileOutputExcel_1.write();
				writeableWorkbook_tFileOutputExcel_1.close();
				if (headerIsInserted_tFileOutputExcel_1 && nb_line_tFileOutputExcel_1 > 0) {
					nb_line_tFileOutputExcel_1 = nb_line_tFileOutputExcel_1 - 1;
				}
				globalMap.put("tFileOutputExcel_1_NB_LINE", nb_line_tFileOutputExcel_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tFileOutputExcel_1", true);
				end_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileOutputExcel_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputJSON_1 finally ] start
				 */

				currentComponent = "tFileInputJSON_1";

				/**
				 * [tFileInputJSON_1 finally ] stop
				 */

				/**
				 * [tFilterRow_1 finally ] start
				 */

				currentComponent = "tFilterRow_1";

				/**
				 * [tFilterRow_1 finally ] stop
				 */

				/**
				 * [tFileOutputExcel_1 finally ] start
				 */

				currentComponent = "tFileOutputExcel_1";

				/**
				 * [tFileOutputExcel_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputJSON_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final export_job_relation export_job_relationClass = new export_job_relation();

		int exitCode = export_job_relationClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = export_job_relation.class.getClassLoader().getResourceAsStream(
					"export_talend7_context/export_job_relation_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = export_job_relation.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
					context.setContextType("projectDir", "id_String");
					if (context.getStringValue("projectDir") == null) {
						context.projectDir = null;
					} else {
						context.projectDir = (String) context.getProperty("projectDir");
					}
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("projectDir")) {
				context.projectDir = (String) parentContextMap.get("projectDir");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputJSON_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputJSON_1) {
			globalMap.put("tFileInputJSON_1_SUBPROCESS_STATE", -1);

			e_tFileInputJSON_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : export_job_relation");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 81620 characters generated by Talend Open Studio for Data Integration on the
 * 2023年12月8日 CST 13:40:34
 ************************************************************************************************/