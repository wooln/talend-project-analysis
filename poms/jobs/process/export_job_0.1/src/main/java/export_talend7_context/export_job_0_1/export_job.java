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

package export_talend7_context.export_job_0_1;

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
 * Job: export_job Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class export_job implements TalendJob {

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

			if (jobDir != null) {

				this.setProperty("jobDir", jobDir.toString());

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

		public String jobDir;

		public String getJobDir() {
			return this.jobDir;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "export_job";
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
					export_job.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(export_job.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileList_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputXML_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileList_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class pStruct implements routines.system.IPersistableRow<pStruct> {
		final static byte[] commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job = new byte[0];
		static byte[] commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id2;

		public String getId2() {
			return this.id2;
		}

		public String id;

		public String getId() {
			return this.id;
		}

		public String label;

		public String getLabel() {
			return this.label;
		}

		public String purpose;

		public String getPurpose() {
			return this.purpose;
		}

		public String description;

		public String getDescription() {
			return this.description;
		}

		public Float version;

		public Float getVersion() {
			return this.version;
		}

		public String path;

		public String getPath() {
			return this.path;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final pStruct other = (pStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(pStruct other) {

			other.id2 = this.id2;
			other.id = this.id;
			other.label = this.label;
			other.purpose = this.purpose;
			other.description = this.description;
			other.version = this.version;
			other.path = this.path;

		}

		public void copyKeysDataTo(pStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EXPORT_TALEND7_CONTEXT_export_job.length) {
					if (length < 1024 && commonByteArray_EXPORT_TALEND7_CONTEXT_export_job.length == 0) {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[1024];
					} else {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job, 0, length);
				strReturn = new String(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job, 0, length, utf8Charset);
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
				if (length > commonByteArray_EXPORT_TALEND7_CONTEXT_export_job.length) {
					if (length < 1024 && commonByteArray_EXPORT_TALEND7_CONTEXT_export_job.length == 0) {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[1024];
					} else {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job, 0, length);
				strReturn = new String(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job) {

				try {

					int length = 0;

					this.id2 = readString(dis);

					this.id = readString(dis);

					this.label = readString(dis);

					this.purpose = readString(dis);

					this.description = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.version = null;
					} else {
						this.version = dis.readFloat();
					}

					this.path = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job) {

				try {

					int length = 0;

					this.id2 = readString(dis);

					this.id = readString(dis);

					this.label = readString(dis);

					this.purpose = readString(dis);

					this.description = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.version = null;
					} else {
						this.version = dis.readFloat();
					}

					this.path = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id2, dos);

				// String

				writeString(this.id, dos);

				// String

				writeString(this.label, dos);

				// String

				writeString(this.purpose, dos);

				// String

				writeString(this.description, dos);

				// Float

				if (this.version == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.version);
				}

				// String

				writeString(this.path, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id2, dos);

				// String

				writeString(this.id, dos);

				// String

				writeString(this.label, dos);

				// String

				writeString(this.purpose, dos);

				// String

				writeString(this.description, dos);

				// Float

				if (this.version == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.version);
				}

				// String

				writeString(this.path, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id2=" + id2);
			sb.append(",id=" + id);
			sb.append(",label=" + label);
			sb.append(",purpose=" + purpose);
			sb.append(",description=" + description);
			sb.append(",version=" + String.valueOf(version));
			sb.append(",path=" + path);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(pStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

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
		final static byte[] commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job = new byte[0];
		static byte[] commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[0];

		public String id2;

		public String getId2() {
			return this.id2;
		}

		public String id;

		public String getId() {
			return this.id;
		}

		public String label;

		public String getLabel() {
			return this.label;
		}

		public String purpose;

		public String getPurpose() {
			return this.purpose;
		}

		public String description;

		public String getDescription() {
			return this.description;
		}

		public Float version;

		public Float getVersion() {
			return this.version;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EXPORT_TALEND7_CONTEXT_export_job.length) {
					if (length < 1024 && commonByteArray_EXPORT_TALEND7_CONTEXT_export_job.length == 0) {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[1024];
					} else {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job, 0, length);
				strReturn = new String(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job, 0, length, utf8Charset);
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
				if (length > commonByteArray_EXPORT_TALEND7_CONTEXT_export_job.length) {
					if (length < 1024 && commonByteArray_EXPORT_TALEND7_CONTEXT_export_job.length == 0) {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[1024];
					} else {
						commonByteArray_EXPORT_TALEND7_CONTEXT_export_job = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job, 0, length);
				strReturn = new String(commonByteArray_EXPORT_TALEND7_CONTEXT_export_job, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job) {

				try {

					int length = 0;

					this.id2 = readString(dis);

					this.id = readString(dis);

					this.label = readString(dis);

					this.purpose = readString(dis);

					this.description = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.version = null;
					} else {
						this.version = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EXPORT_TALEND7_CONTEXT_export_job) {

				try {

					int length = 0;

					this.id2 = readString(dis);

					this.id = readString(dis);

					this.label = readString(dis);

					this.purpose = readString(dis);

					this.description = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.version = null;
					} else {
						this.version = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id2, dos);

				// String

				writeString(this.id, dos);

				// String

				writeString(this.label, dos);

				// String

				writeString(this.purpose, dos);

				// String

				writeString(this.description, dos);

				// Float

				if (this.version == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.version);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id2, dos);

				// String

				writeString(this.id, dos);

				// String

				writeString(this.label, dos);

				// String

				writeString(this.purpose, dos);

				// String

				writeString(this.description, dos);

				// Float

				if (this.version == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.version);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id2=" + id2);
			sb.append(",id=" + id);
			sb.append(",label=" + label);
			sb.append(",purpose=" + purpose);
			sb.append(",description=" + description);
			sb.append(",version=" + String.valueOf(version));
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

	public void tFileList_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileList_1_SUBPROCESS_STATE", 0);

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
				pStruct p = new pStruct();

				/**
				 * [tFileList_1 begin ] start
				 */

				int NB_ITERATE_tFileInputXML_1 = 0; // for statistics

				ok_Hash.put("tFileList_1", false);
				start_Hash.put("tFileList_1", System.currentTimeMillis());

				currentComponent = "tFileList_1";

				int tos_count_tFileList_1 = 0;

				String directory_tFileList_1 = context.jobDir;
				final java.util.List<String> maskList_tFileList_1 = new java.util.ArrayList<String>();
				final java.util.List<java.util.regex.Pattern> patternList_tFileList_1 = new java.util.ArrayList<java.util.regex.Pattern>();
				maskList_tFileList_1.add("*.properties");
				for (final String filemask_tFileList_1 : maskList_tFileList_1) {
					String filemask_compile_tFileList_1 = filemask_tFileList_1;

					filemask_compile_tFileList_1 = org.apache.oro.text.GlobCompiler.globToPerl5(
							filemask_tFileList_1.toCharArray(), org.apache.oro.text.GlobCompiler.DEFAULT_MASK);

					java.util.regex.Pattern fileNamePattern_tFileList_1 = java.util.regex.Pattern
							.compile(filemask_compile_tFileList_1);
					patternList_tFileList_1.add(fileNamePattern_tFileList_1);
				}
				int NB_FILEtFileList_1 = 0;

				final boolean case_sensitive_tFileList_1 = true;

				final java.util.List<java.io.File> list_tFileList_1 = new java.util.ArrayList<java.io.File>();
				final java.util.Set<String> filePath_tFileList_1 = new java.util.HashSet<String>();
				java.io.File file_tFileList_1 = new java.io.File(directory_tFileList_1);

				file_tFileList_1.listFiles(new java.io.FilenameFilter() {
					public boolean accept(java.io.File dir, String name) {
						java.io.File file = new java.io.File(dir, name);

						if (!file.isDirectory()) {

							String fileName_tFileList_1 = file.getName();
							for (final java.util.regex.Pattern fileNamePattern_tFileList_1 : patternList_tFileList_1) {
								if (fileNamePattern_tFileList_1.matcher(fileName_tFileList_1).matches()) {
									if (!filePath_tFileList_1.contains(file.getAbsolutePath())) {
										list_tFileList_1.add(file);
										filePath_tFileList_1.add(file.getAbsolutePath());
									}
								}
							}
							return true;
						} else {
							file.listFiles(this);
						}

						return false;
					}
				});
				java.util.Collections.sort(list_tFileList_1);

				for (int i_tFileList_1 = 0; i_tFileList_1 < list_tFileList_1.size(); i_tFileList_1++) {
					java.io.File files_tFileList_1 = list_tFileList_1.get(i_tFileList_1);
					String fileName_tFileList_1 = files_tFileList_1.getName();

					String currentFileName_tFileList_1 = files_tFileList_1.getName();
					String currentFilePath_tFileList_1 = files_tFileList_1.getAbsolutePath();
					String currentFileDirectory_tFileList_1 = files_tFileList_1.getParent();
					String currentFileExtension_tFileList_1 = null;

					if (files_tFileList_1.getName().contains(".") && files_tFileList_1.isFile()) {
						currentFileExtension_tFileList_1 = files_tFileList_1.getName()
								.substring(files_tFileList_1.getName().lastIndexOf(".") + 1);
					} else {
						currentFileExtension_tFileList_1 = "";
					}

					NB_FILEtFileList_1++;
					globalMap.put("tFileList_1_CURRENT_FILE", currentFileName_tFileList_1);
					globalMap.put("tFileList_1_CURRENT_FILEPATH", currentFilePath_tFileList_1);
					globalMap.put("tFileList_1_CURRENT_FILEDIRECTORY", currentFileDirectory_tFileList_1);
					globalMap.put("tFileList_1_CURRENT_FILEEXTENSION", currentFileExtension_tFileList_1);
					globalMap.put("tFileList_1_NB_FILE", NB_FILEtFileList_1);

					/**
					 * [tFileList_1 begin ] stop
					 */

					/**
					 * [tFileList_1 main ] start
					 */

					currentComponent = "tFileList_1";

					tos_count_tFileList_1++;

					/**
					 * [tFileList_1 main ] stop
					 */

					/**
					 * [tFileList_1 process_data_begin ] start
					 */

					currentComponent = "tFileList_1";

					/**
					 * [tFileList_1 process_data_begin ] stop
					 */
					NB_ITERATE_tFileInputXML_1++;

					if (execStat) {
						runStat.updateStatOnConnection("p", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row1", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate2", 1, "exec" + NB_ITERATE_tFileInputXML_1);
						// Thread.sleep(1000);
					}

					/**
					 * [tFileOutputExcel_1 begin ] start
					 */

					ok_Hash.put("tFileOutputExcel_1", false);
					start_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

					currentComponent = "tFileOutputExcel_1";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "p");
					}

					int tos_count_tFileOutputExcel_1 = 0;

					int columnIndex_tFileOutputExcel_1 = 0;
					boolean headerIsInserted_tFileOutputExcel_1 = false;

					String fileName_tFileOutputExcel_1 = context.jobDir + "/jobs_list.xlsx";
					int nb_line_tFileOutputExcel_1 = 0;
					org.talend.ExcelTool xlsxTool_tFileOutputExcel_1 = new org.talend.ExcelTool();

					xlsxTool_tFileOutputExcel_1.setTruncateExceedingCharacters(false);
					xlsxTool_tFileOutputExcel_1.setSheet("job_list");
					xlsxTool_tFileOutputExcel_1.setAppend(true, true, false);
					xlsxTool_tFileOutputExcel_1.setRecalculateFormula(false);
					xlsxTool_tFileOutputExcel_1.setXY(false, 0, 0, false);

					java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object> chm_tFileOutputExcel_1 = (java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object>) globalMap
							.get("concurrentHashMap");
					java.lang.Object lockObj_tFileOutputExcel_1 = chm_tFileOutputExcel_1
							.computeIfAbsent("EXCEL_OUTPUT_LOCK_OBJ_tFileOutputExcel_1", k -> new Object());
					synchronized (lockObj_tFileOutputExcel_1) {

						xlsxTool_tFileOutputExcel_1.prepareXlsxFile(fileName_tFileOutputExcel_1);

					}

					xlsxTool_tFileOutputExcel_1.setFont("");

					if (xlsxTool_tFileOutputExcel_1.getStartRow() == 0) {

						xlsxTool_tFileOutputExcel_1.addRow();

						xlsxTool_tFileOutputExcel_1.addCellValue("id2");

						xlsxTool_tFileOutputExcel_1.addCellValue("id");

						xlsxTool_tFileOutputExcel_1.addCellValue("label");

						xlsxTool_tFileOutputExcel_1.addCellValue("purpose");

						xlsxTool_tFileOutputExcel_1.addCellValue("description");

						xlsxTool_tFileOutputExcel_1.addCellValue("version");

						xlsxTool_tFileOutputExcel_1.addCellValue("path");

						nb_line_tFileOutputExcel_1++;
						headerIsInserted_tFileOutputExcel_1 = true;

					}

					/**
					 * [tFileOutputExcel_1 begin ] stop
					 */

					/**
					 * [tMap_1 begin ] start
					 */

					ok_Hash.put("tMap_1", false);
					start_Hash.put("tMap_1", System.currentTimeMillis());

					currentComponent = "tMap_1";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
					}

					int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_1__Struct {
					}
					Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
					pStruct p_tmp = new pStruct();
// ###############################

					/**
					 * [tMap_1 begin ] stop
					 */

					/**
					 * [tFileInputXML_1 begin ] start
					 */

					ok_Hash.put("tFileInputXML_1", false);
					start_Hash.put("tFileInputXML_1", System.currentTimeMillis());

					currentComponent = "tFileInputXML_1";

					int tos_count_tFileInputXML_1 = 0;

					int nb_line_tFileInputXML_1 = 0;

					String os_tFileInputXML_1 = System.getProperty("os.name").toLowerCase();
					boolean isWindows_tFileInputXML_1 = false;
					if (os_tFileInputXML_1.indexOf("windows") > -1 || os_tFileInputXML_1.indexOf("nt") > -1) {
						isWindows_tFileInputXML_1 = true;
					}
					class NameSpaceTool_tFileInputXML_1 {

						public java.util.HashMap<String, String> xmlNameSpaceMap = new java.util.HashMap<String, String>();

						private java.util.List<String> defualtNSPath = new java.util.ArrayList<String>();

						public void countNSMap(org.dom4j.Element el) {
							for (org.dom4j.Namespace ns : (java.util.List<org.dom4j.Namespace>) el
									.declaredNamespaces()) {
								if (ns.getPrefix().trim().length() == 0) {
									xmlNameSpaceMap.put("pre" + defualtNSPath.size(), ns.getURI());
									String path = "";
									org.dom4j.Element elTmp = el;
									while (elTmp != null) {
										if (elTmp.getNamespacePrefix() != null
												&& elTmp.getNamespacePrefix().length() > 0) {
											path = "/" + elTmp.getNamespacePrefix() + ":" + elTmp.getName() + path;
										} else {
											path = "/" + elTmp.getName() + path;
										}
										elTmp = elTmp.getParent();
									}
									defualtNSPath.add(path);
								} else {
									xmlNameSpaceMap.put(ns.getPrefix(), ns.getURI());
								}

							}
							for (org.dom4j.Element e : (java.util.List<org.dom4j.Element>) el.elements()) {
								countNSMap(e);
							}
						}

						private final org.talend.xpath.XPathUtil util = new org.talend.xpath.XPathUtil();

						{
							util.setDefaultNSPath(defualtNSPath);
						}

						public String addDefaultNSPrefix(String path) {
							return util.addDefaultNSPrefix(path);
						}

						public String addDefaultNSPrefix(String relativeXpression, String basePath) {
							return util.addDefaultNSPrefix(relativeXpression, basePath);
						}

					}

					class XML_API_tFileInputXML_1 {
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

					org.dom4j.io.SAXReader reader_tFileInputXML_1 = new org.dom4j.io.SAXReader();
					Object filename_tFileInputXML_1 = null;
					try {
						filename_tFileInputXML_1 = ((String) globalMap.get("tFileList_1_CURRENT_FILEPATH"));
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputXML_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}
					if (filename_tFileInputXML_1 != null && filename_tFileInputXML_1 instanceof String
							&& filename_tFileInputXML_1.toString().startsWith("//")) {
						if (!isWindows_tFileInputXML_1) {
							filename_tFileInputXML_1 = filename_tFileInputXML_1.toString().replaceFirst("//", "/");
						}
					}

					boolean isValidFile_tFileInputXML_1 = true;
					org.dom4j.Document doc_tFileInputXML_1 = null;
					java.io.Closeable toClose_tFileInputXML_1 = null;
					try {
						if (filename_tFileInputXML_1 instanceof java.io.InputStream) {
							java.io.InputStream inputStream_tFileInputXML_1 = (java.io.InputStream) filename_tFileInputXML_1;
							toClose_tFileInputXML_1 = inputStream_tFileInputXML_1;
							doc_tFileInputXML_1 = reader_tFileInputXML_1.read(inputStream_tFileInputXML_1);
						} else {
							java.io.Reader unicodeReader_tFileInputXML_1 = new UnicodeReader(
									new java.io.FileInputStream(String.valueOf(filename_tFileInputXML_1)), "UTF-8");
							toClose_tFileInputXML_1 = unicodeReader_tFileInputXML_1;
							org.xml.sax.InputSource in_tFileInputXML_1 = new org.xml.sax.InputSource(
									unicodeReader_tFileInputXML_1);
							doc_tFileInputXML_1 = reader_tFileInputXML_1.read(in_tFileInputXML_1);
						}
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputXML_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());
						isValidFile_tFileInputXML_1 = false;
					} finally {
						if (toClose_tFileInputXML_1 != null) {
							toClose_tFileInputXML_1.close();
						}
					}
					if (isValidFile_tFileInputXML_1) {
						NameSpaceTool_tFileInputXML_1 nsTool_tFileInputXML_1 = new NameSpaceTool_tFileInputXML_1();
						nsTool_tFileInputXML_1.countNSMap(doc_tFileInputXML_1.getRootElement());
						java.util.HashMap<String, String> xmlNameSpaceMap_tFileInputXML_1 = nsTool_tFileInputXML_1.xmlNameSpaceMap;

						org.dom4j.XPath x_tFileInputXML_1 = doc_tFileInputXML_1.createXPath(
								nsTool_tFileInputXML_1.addDefaultNSPrefix("/xmi:XMI/TalendProperties:Property"));
						x_tFileInputXML_1.setNamespaceURIs(xmlNameSpaceMap_tFileInputXML_1);

						java.util.List<org.dom4j.Node> nodeList_tFileInputXML_1 = (java.util.List<org.dom4j.Node>) x_tFileInputXML_1
								.selectNodes(doc_tFileInputXML_1);
						XML_API_tFileInputXML_1 xml_api_tFileInputXML_1 = new XML_API_tFileInputXML_1();
						String str_tFileInputXML_1 = "";
						org.dom4j.Node node_tFileInputXML_1 = null;

//init all mapping xpaths
						java.util.Map<Integer, org.dom4j.XPath> xpaths_tFileInputXML_1 = new java.util.HashMap<Integer, org.dom4j.XPath>();
						class XPathUtil_tFileInputXML_1 {

							public void initXPaths_0(java.util.Map<Integer, org.dom4j.XPath> xpaths,
									NameSpaceTool_tFileInputXML_1 nsTool,
									java.util.HashMap<String, String> xmlNameSpaceMap) {

								org.dom4j.XPath xpath_0 = org.dom4j.DocumentHelper.createXPath(
										nsTool.addDefaultNSPrefix("@xmi:id", "/xmi:XMI/TalendProperties:Property"));
								xpath_0.setNamespaceURIs(xmlNameSpaceMap);

								xpaths.put(0, xpath_0);

								org.dom4j.XPath xpath_1 = org.dom4j.DocumentHelper.createXPath(
										nsTool.addDefaultNSPrefix("@id", "/xmi:XMI/TalendProperties:Property"));
								xpath_1.setNamespaceURIs(xmlNameSpaceMap);

								xpaths.put(1, xpath_1);

								org.dom4j.XPath xpath_2 = org.dom4j.DocumentHelper.createXPath(
										nsTool.addDefaultNSPrefix("@label", "/xmi:XMI/TalendProperties:Property"));
								xpath_2.setNamespaceURIs(xmlNameSpaceMap);

								xpaths.put(2, xpath_2);

								org.dom4j.XPath xpath_3 = org.dom4j.DocumentHelper.createXPath(
										nsTool.addDefaultNSPrefix("@purpose", "/xmi:XMI/TalendProperties:Property"));
								xpath_3.setNamespaceURIs(xmlNameSpaceMap);

								xpaths.put(3, xpath_3);

								org.dom4j.XPath xpath_4 = org.dom4j.DocumentHelper.createXPath(nsTool
										.addDefaultNSPrefix("@description", "/xmi:XMI/TalendProperties:Property"));
								xpath_4.setNamespaceURIs(xmlNameSpaceMap);

								xpaths.put(4, xpath_4);

								org.dom4j.XPath xpath_5 = org.dom4j.DocumentHelper.createXPath(
										nsTool.addDefaultNSPrefix("@version", "/xmi:XMI/TalendProperties:Property"));
								xpath_5.setNamespaceURIs(xmlNameSpaceMap);

								xpaths.put(5, xpath_5);

							}

							public void initXPaths(java.util.Map<Integer, org.dom4j.XPath> xpaths,
									NameSpaceTool_tFileInputXML_1 nsTool,
									java.util.HashMap<String, String> xmlNameSpaceMap) {

								initXPaths_0(xpaths, nsTool, xmlNameSpaceMap);

							}
						}
						XPathUtil_tFileInputXML_1 xPathUtil_tFileInputXML_1 = new XPathUtil_tFileInputXML_1();
						xPathUtil_tFileInputXML_1.initXPaths(xpaths_tFileInputXML_1, nsTool_tFileInputXML_1,
								xmlNameSpaceMap_tFileInputXML_1);
						for (org.dom4j.Node temp_tFileInputXML_1 : nodeList_tFileInputXML_1) {
							nb_line_tFileInputXML_1++;

							row1 = null;
							boolean whetherReject_tFileInputXML_1 = false;
							row1 = new row1Struct();
							try {
								Object obj0_tFileInputXML_1 = xpaths_tFileInputXML_1.get(0)
										.evaluate(temp_tFileInputXML_1);
								if (obj0_tFileInputXML_1 == null) {
									node_tFileInputXML_1 = null;
									str_tFileInputXML_1 = "";

								} else if (obj0_tFileInputXML_1 instanceof org.dom4j.Node) {
									node_tFileInputXML_1 = (org.dom4j.Node) obj0_tFileInputXML_1;
									str_tFileInputXML_1 = org.jaxen.function.StringFunction.evaluate(
											node_tFileInputXML_1, org.jaxen.dom4j.DocumentNavigator.getInstance());
								} else if (obj0_tFileInputXML_1 instanceof String
										|| obj0_tFileInputXML_1 instanceof Number) {
									node_tFileInputXML_1 = temp_tFileInputXML_1;
									str_tFileInputXML_1 = String.valueOf(obj0_tFileInputXML_1);
								} else if (obj0_tFileInputXML_1 instanceof java.util.List) {
									java.util.List<org.dom4j.Node> nodes_tFileInputXML_1 = (java.util.List<org.dom4j.Node>) obj0_tFileInputXML_1;
									node_tFileInputXML_1 = nodes_tFileInputXML_1.size() > 0
											? nodes_tFileInputXML_1.get(0)
											: null;
									str_tFileInputXML_1 = node_tFileInputXML_1 == null ? ""
											: org.jaxen.function.StringFunction.evaluate(node_tFileInputXML_1,
													org.jaxen.dom4j.DocumentNavigator.getInstance());
								}
								if (xml_api_tFileInputXML_1.isDefNull(node_tFileInputXML_1)) {
									row1.id2 = null;
								} else if (xml_api_tFileInputXML_1.isEmpty(node_tFileInputXML_1)) {
									row1.id2 = "";
								} else if (xml_api_tFileInputXML_1.isMissing(node_tFileInputXML_1)) {
									row1.id2 = null;
								} else {
									row1.id2 = str_tFileInputXML_1;
								}
								Object obj1_tFileInputXML_1 = xpaths_tFileInputXML_1.get(1)
										.evaluate(temp_tFileInputXML_1);
								if (obj1_tFileInputXML_1 == null) {
									node_tFileInputXML_1 = null;
									str_tFileInputXML_1 = "";

								} else if (obj1_tFileInputXML_1 instanceof org.dom4j.Node) {
									node_tFileInputXML_1 = (org.dom4j.Node) obj1_tFileInputXML_1;
									str_tFileInputXML_1 = org.jaxen.function.StringFunction.evaluate(
											node_tFileInputXML_1, org.jaxen.dom4j.DocumentNavigator.getInstance());
								} else if (obj1_tFileInputXML_1 instanceof String
										|| obj1_tFileInputXML_1 instanceof Number) {
									node_tFileInputXML_1 = temp_tFileInputXML_1;
									str_tFileInputXML_1 = String.valueOf(obj1_tFileInputXML_1);
								} else if (obj1_tFileInputXML_1 instanceof java.util.List) {
									java.util.List<org.dom4j.Node> nodes_tFileInputXML_1 = (java.util.List<org.dom4j.Node>) obj1_tFileInputXML_1;
									node_tFileInputXML_1 = nodes_tFileInputXML_1.size() > 0
											? nodes_tFileInputXML_1.get(0)
											: null;
									str_tFileInputXML_1 = node_tFileInputXML_1 == null ? ""
											: org.jaxen.function.StringFunction.evaluate(node_tFileInputXML_1,
													org.jaxen.dom4j.DocumentNavigator.getInstance());
								}
								if (xml_api_tFileInputXML_1.isDefNull(node_tFileInputXML_1)) {
									row1.id = null;
								} else if (xml_api_tFileInputXML_1.isEmpty(node_tFileInputXML_1)) {
									row1.id = "";
								} else if (xml_api_tFileInputXML_1.isMissing(node_tFileInputXML_1)) {
									row1.id = null;
								} else {
									row1.id = str_tFileInputXML_1;
								}
								Object obj2_tFileInputXML_1 = xpaths_tFileInputXML_1.get(2)
										.evaluate(temp_tFileInputXML_1);
								if (obj2_tFileInputXML_1 == null) {
									node_tFileInputXML_1 = null;
									str_tFileInputXML_1 = "";

								} else if (obj2_tFileInputXML_1 instanceof org.dom4j.Node) {
									node_tFileInputXML_1 = (org.dom4j.Node) obj2_tFileInputXML_1;
									str_tFileInputXML_1 = org.jaxen.function.StringFunction.evaluate(
											node_tFileInputXML_1, org.jaxen.dom4j.DocumentNavigator.getInstance());
								} else if (obj2_tFileInputXML_1 instanceof String
										|| obj2_tFileInputXML_1 instanceof Number) {
									node_tFileInputXML_1 = temp_tFileInputXML_1;
									str_tFileInputXML_1 = String.valueOf(obj2_tFileInputXML_1);
								} else if (obj2_tFileInputXML_1 instanceof java.util.List) {
									java.util.List<org.dom4j.Node> nodes_tFileInputXML_1 = (java.util.List<org.dom4j.Node>) obj2_tFileInputXML_1;
									node_tFileInputXML_1 = nodes_tFileInputXML_1.size() > 0
											? nodes_tFileInputXML_1.get(0)
											: null;
									str_tFileInputXML_1 = node_tFileInputXML_1 == null ? ""
											: org.jaxen.function.StringFunction.evaluate(node_tFileInputXML_1,
													org.jaxen.dom4j.DocumentNavigator.getInstance());
								}
								if (xml_api_tFileInputXML_1.isDefNull(node_tFileInputXML_1)) {
									row1.label = null;
								} else if (xml_api_tFileInputXML_1.isEmpty(node_tFileInputXML_1)) {
									row1.label = "";
								} else if (xml_api_tFileInputXML_1.isMissing(node_tFileInputXML_1)) {
									row1.label = null;
								} else {
									row1.label = str_tFileInputXML_1;
								}
								Object obj3_tFileInputXML_1 = xpaths_tFileInputXML_1.get(3)
										.evaluate(temp_tFileInputXML_1);
								if (obj3_tFileInputXML_1 == null) {
									node_tFileInputXML_1 = null;
									str_tFileInputXML_1 = "";

								} else if (obj3_tFileInputXML_1 instanceof org.dom4j.Node) {
									node_tFileInputXML_1 = (org.dom4j.Node) obj3_tFileInputXML_1;
									str_tFileInputXML_1 = org.jaxen.function.StringFunction.evaluate(
											node_tFileInputXML_1, org.jaxen.dom4j.DocumentNavigator.getInstance());
								} else if (obj3_tFileInputXML_1 instanceof String
										|| obj3_tFileInputXML_1 instanceof Number) {
									node_tFileInputXML_1 = temp_tFileInputXML_1;
									str_tFileInputXML_1 = String.valueOf(obj3_tFileInputXML_1);
								} else if (obj3_tFileInputXML_1 instanceof java.util.List) {
									java.util.List<org.dom4j.Node> nodes_tFileInputXML_1 = (java.util.List<org.dom4j.Node>) obj3_tFileInputXML_1;
									node_tFileInputXML_1 = nodes_tFileInputXML_1.size() > 0
											? nodes_tFileInputXML_1.get(0)
											: null;
									str_tFileInputXML_1 = node_tFileInputXML_1 == null ? ""
											: org.jaxen.function.StringFunction.evaluate(node_tFileInputXML_1,
													org.jaxen.dom4j.DocumentNavigator.getInstance());
								}
								if (xml_api_tFileInputXML_1.isDefNull(node_tFileInputXML_1)) {
									row1.purpose = null;
								} else if (xml_api_tFileInputXML_1.isEmpty(node_tFileInputXML_1)) {
									row1.purpose = "";
								} else if (xml_api_tFileInputXML_1.isMissing(node_tFileInputXML_1)) {
									row1.purpose = null;
								} else {
									row1.purpose = str_tFileInputXML_1;
								}
								Object obj4_tFileInputXML_1 = xpaths_tFileInputXML_1.get(4)
										.evaluate(temp_tFileInputXML_1);
								if (obj4_tFileInputXML_1 == null) {
									node_tFileInputXML_1 = null;
									str_tFileInputXML_1 = "";

								} else if (obj4_tFileInputXML_1 instanceof org.dom4j.Node) {
									node_tFileInputXML_1 = (org.dom4j.Node) obj4_tFileInputXML_1;
									str_tFileInputXML_1 = org.jaxen.function.StringFunction.evaluate(
											node_tFileInputXML_1, org.jaxen.dom4j.DocumentNavigator.getInstance());
								} else if (obj4_tFileInputXML_1 instanceof String
										|| obj4_tFileInputXML_1 instanceof Number) {
									node_tFileInputXML_1 = temp_tFileInputXML_1;
									str_tFileInputXML_1 = String.valueOf(obj4_tFileInputXML_1);
								} else if (obj4_tFileInputXML_1 instanceof java.util.List) {
									java.util.List<org.dom4j.Node> nodes_tFileInputXML_1 = (java.util.List<org.dom4j.Node>) obj4_tFileInputXML_1;
									node_tFileInputXML_1 = nodes_tFileInputXML_1.size() > 0
											? nodes_tFileInputXML_1.get(0)
											: null;
									str_tFileInputXML_1 = node_tFileInputXML_1 == null ? ""
											: org.jaxen.function.StringFunction.evaluate(node_tFileInputXML_1,
													org.jaxen.dom4j.DocumentNavigator.getInstance());
								}
								if (xml_api_tFileInputXML_1.isDefNull(node_tFileInputXML_1)) {
									row1.description = null;
								} else if (xml_api_tFileInputXML_1.isEmpty(node_tFileInputXML_1)) {
									row1.description = "";
								} else if (xml_api_tFileInputXML_1.isMissing(node_tFileInputXML_1)) {
									row1.description = null;
								} else {
									row1.description = str_tFileInputXML_1;
								}
								Object obj5_tFileInputXML_1 = xpaths_tFileInputXML_1.get(5)
										.evaluate(temp_tFileInputXML_1);
								if (obj5_tFileInputXML_1 == null) {
									node_tFileInputXML_1 = null;
									str_tFileInputXML_1 = "";

								} else if (obj5_tFileInputXML_1 instanceof org.dom4j.Node) {
									node_tFileInputXML_1 = (org.dom4j.Node) obj5_tFileInputXML_1;
									str_tFileInputXML_1 = org.jaxen.function.StringFunction.evaluate(
											node_tFileInputXML_1, org.jaxen.dom4j.DocumentNavigator.getInstance());
								} else if (obj5_tFileInputXML_1 instanceof String
										|| obj5_tFileInputXML_1 instanceof Number) {
									node_tFileInputXML_1 = temp_tFileInputXML_1;
									str_tFileInputXML_1 = String.valueOf(obj5_tFileInputXML_1);
								} else if (obj5_tFileInputXML_1 instanceof java.util.List) {
									java.util.List<org.dom4j.Node> nodes_tFileInputXML_1 = (java.util.List<org.dom4j.Node>) obj5_tFileInputXML_1;
									node_tFileInputXML_1 = nodes_tFileInputXML_1.size() > 0
											? nodes_tFileInputXML_1.get(0)
											: null;
									str_tFileInputXML_1 = node_tFileInputXML_1 == null ? ""
											: org.jaxen.function.StringFunction.evaluate(node_tFileInputXML_1,
													org.jaxen.dom4j.DocumentNavigator.getInstance());
								}
								if (xml_api_tFileInputXML_1.isDefNull(node_tFileInputXML_1)) {
									row1.version = null;
								} else if (xml_api_tFileInputXML_1.isEmpty(node_tFileInputXML_1)
										|| xml_api_tFileInputXML_1.isMissing(node_tFileInputXML_1)) {
									row1.version = null;
								} else {
									row1.version = ParserUtils.parseTo_Float(str_tFileInputXML_1);
								}

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputXML_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputXML_1 = true;
								System.err.println(e.getMessage());
								row1 = null;
							}

							/**
							 * [tFileInputXML_1 begin ] stop
							 */

							/**
							 * [tFileInputXML_1 main ] start
							 */

							currentComponent = "tFileInputXML_1";

							tos_count_tFileInputXML_1++;

							/**
							 * [tFileInputXML_1 main ] stop
							 */

							/**
							 * [tFileInputXML_1 process_data_begin ] start
							 */

							currentComponent = "tFileInputXML_1";

							/**
							 * [tFileInputXML_1 process_data_begin ] stop
							 */
// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tMap_1 main ] start
								 */

								currentComponent = "tMap_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row1"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_1 = false;
								boolean mainRowRejected_tMap_1 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
									// ###############################
									// # Output tables

									p = null;

// # Output table : 'p'
									p_tmp.id2 = row1.id2;
									p_tmp.id = row1.id;
									p_tmp.label = row1.label;
									p_tmp.purpose = row1.purpose;
									p_tmp.description = row1.description;
									p_tmp.version = row1.version;
									p_tmp.path = ((String) globalMap.get("tFileList_1_CURRENT_FILEPATH"))
											.replace(context.jobDir, "");
									p = p_tmp;
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_1 = false;

								tos_count_tMap_1++;

								/**
								 * [tMap_1 main ] stop
								 */

								/**
								 * [tMap_1 process_data_begin ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_begin ] stop
								 */
// Start of branch "p"
								if (p != null) {

									/**
									 * [tFileOutputExcel_1 main ] start
									 */

									currentComponent = "tFileOutputExcel_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "p"

										);
									}

									xlsxTool_tFileOutputExcel_1.addRow();

									if (p.id2 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(p.id2));
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (p.id != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(p.id));
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (p.label != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(p.label));
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (p.purpose != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(p.purpose));
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (p.description != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(p.description));
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (p.version != null) {

										xlsxTool_tFileOutputExcel_1
												.addCellValue(Double.parseDouble(String.valueOf(p.version)));
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (p.path != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(p.path));
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
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

								} // End of branch "p"

								/**
								 * [tMap_1 process_data_end ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tFileInputXML_1 process_data_end ] start
							 */

							currentComponent = "tFileInputXML_1";

							/**
							 * [tFileInputXML_1 process_data_end ] stop
							 */

							/**
							 * [tFileInputXML_1 end ] start
							 */

							currentComponent = "tFileInputXML_1";

						}
					}
					globalMap.put("tFileInputXML_1_NB_LINE", nb_line_tFileInputXML_1);

					ok_Hash.put("tFileInputXML_1", true);
					end_Hash.put("tFileInputXML_1", System.currentTimeMillis());

					/**
					 * [tFileInputXML_1 end ] stop
					 */

					/**
					 * [tMap_1 end ] start
					 */

					currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
					}

					ok_Hash.put("tMap_1", true);
					end_Hash.put("tMap_1", System.currentTimeMillis());

					/**
					 * [tMap_1 end ] stop
					 */

					/**
					 * [tFileOutputExcel_1 end ] start
					 */

					currentComponent = "tFileOutputExcel_1";

					xlsxTool_tFileOutputExcel_1.writeExcel(fileName_tFileOutputExcel_1, true);

					if (headerIsInserted_tFileOutputExcel_1 && nb_line_tFileOutputExcel_1 > 0) {
						nb_line_tFileOutputExcel_1 = nb_line_tFileOutputExcel_1 - 1;
					}
					globalMap.put("tFileOutputExcel_1_NB_LINE", nb_line_tFileOutputExcel_1);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "p");
					}

					ok_Hash.put("tFileOutputExcel_1", true);
					end_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

					/**
					 * [tFileOutputExcel_1 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate2", 2, "exec" + NB_ITERATE_tFileInputXML_1);
					}

					/**
					 * [tFileList_1 process_data_end ] start
					 */

					currentComponent = "tFileList_1";

					/**
					 * [tFileList_1 process_data_end ] stop
					 */

					/**
					 * [tFileList_1 end ] start
					 */

					currentComponent = "tFileList_1";

				}
				globalMap.put("tFileList_1_NB_FILE", NB_FILEtFileList_1);

				ok_Hash.put("tFileList_1", true);
				end_Hash.put("tFileList_1", System.currentTimeMillis());

				/**
				 * [tFileList_1 end ] stop
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
				 * [tFileList_1 finally ] start
				 */

				currentComponent = "tFileList_1";

				/**
				 * [tFileList_1 finally ] stop
				 */

				/**
				 * [tFileInputXML_1 finally ] start
				 */

				currentComponent = "tFileInputXML_1";

				/**
				 * [tFileInputXML_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
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

		globalMap.put("tFileList_1_SUBPROCESS_STATE", 1);
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
		final export_job export_jobClass = new export_job();

		int exitCode = export_jobClass.runJobInTOS(args);

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
			java.io.InputStream inContext = export_job.class.getClassLoader().getResourceAsStream(
					"export_talend7_context/export_job_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = export_job.class.getClassLoader()
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
					context.setContextType("jobDir", "id_String");
					if (context.getStringValue("jobDir") == null) {
						context.jobDir = null;
					} else {
						context.jobDir = (String) context.getProperty("jobDir");
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
			if (parentContextMap.containsKey("jobDir")) {
				context.jobDir = (String) parentContextMap.get("jobDir");
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
			tFileList_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileList_1) {
			globalMap.put("tFileList_1_SUBPROCESS_STATE", -1);

			e_tFileList_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : export_job");
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
 * 73264 characters generated by Talend Open Studio for Data Integration on the
 * 2023年12月1日 CST 16:02:36
 ************************************************************************************************/