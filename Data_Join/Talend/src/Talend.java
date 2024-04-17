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
public class Talend  {

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset
			.defaultCharset().name();

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

		}

	}

	private ContextProperties context = new ContextProperties();

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Projet_PFA";
	private final String projectName = "FORMATION_PROJECT";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	public void setDataSources(
			java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources
				.entrySet()) {
			talendDataSources.put(
					dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry
							.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(
			new java.io.BufferedOutputStream(baos));

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

		private TalendException(Exception e, String errorComponent,
				final java.util.Map<String, Object> globalMap) {
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
				if (virtualComponentName != null
						&& currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE",
							getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE",
						getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent
						+ " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Projet_PFA.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass()
							.getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Projet_PFA.this, new Object[] { e,
									currentComponent, globalMap });
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

	public void tFileInputDelimited_1_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tReplicate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFilterColumns_1_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFilterColumns_2_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFileInputDelimited_3_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFilterColumns_3_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFileInputDelimited_4_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFilterColumns_4_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFileInputDelimited_5_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFilterColumns_5_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tAdvancedHash_row5_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tAdvancedHash_row2_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tAdvancedHash_row6_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tAdvancedHash_row3_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent,
				globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_2_onSubJobError(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_3_onSubJobError(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_4_onSubJobError(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_5_onSubJobError(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row5Struct implements
			routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String City;

		public String getCity() {
			return this.City;
		}

		public String Country;

		public String getCountry() {
			return this.Country;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.City = readString(dis);

					this.Country = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.City, dos);

				// String

				writeString(this.Country, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",City=" + City);
			sb.append(",Country=" + Country);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public static class FiltrerColumsStruct implements
			routines.system.IPersistableRow<FiltrerColumsStruct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Id;

		public Integer getId() {
			return this.Id;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String City;

		public String getCity() {
			return this.City;
		}

		public String Country;

		public String getCountry() {
			return this.Country;
		}

		public String Phone;

		public String getPhone() {
			return this.Phone;
		}

		public String Fax;

		public String getFax() {
			return this.Fax;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Id = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.City = readString(dis);

					this.Country = readString(dis);

					this.Phone = readString(dis);

					this.Fax = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Id, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.City, dos);

				// String

				writeString(this.Country, dos);

				// String

				writeString(this.Phone, dos);

				// String

				writeString(this.Fax, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",City=" + City);
			sb.append(",Country=" + Country);
			sb.append(",Phone=" + Phone);
			sb.append(",Fax=" + Fax);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(FiltrerColumsStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public static class customerDataStruct implements
			routines.system.IPersistableRow<customerDataStruct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Id;

		public Integer getId() {
			return this.Id;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String City;

		public String getCity() {
			return this.City;
		}

		public String Country;

		public String getCountry() {
			return this.Country;
		}

		public String Phone;

		public String getPhone() {
			return this.Phone;
		}

		public String Fax;

		public String getFax() {
			return this.Fax;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Id = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.City = readString(dis);

					this.Country = readString(dis);

					this.Phone = readString(dis);

					this.Fax = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Id, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.City, dos);

				// String

				writeString(this.Country, dos);

				// String

				writeString(this.Phone, dos);

				// String

				writeString(this.Fax, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",City=" + City);
			sb.append(",Country=" + Country);
			sb.append(",Phone=" + Phone);
			sb.append(",Fax=" + Fax);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(customerDataStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public void tFileInputDelimited_1Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				customerDataStruct customerData = new customerDataStruct();
				FiltrerColumsStruct FiltrerColums = new FiltrerColumsStruct();
				row5Struct row5 = new row5Struct();

				/**
				 * [tAdvancedHash_row5 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row5", false);
				start_Hash
						.put("tAdvancedHash_row5", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row5";

				int tos_count_tAdvancedHash_row5 = 0;

				class BytesLimit65535_tAdvancedHash_row5 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tAdvancedHash_row5().limitLog4jByte();

				// connection name:row5
				// source node:tFilterColumns_1 - inputs:(FiltrerColums)
				// outputs:(row5,row5) | target node:tAdvancedHash_row5 -
				// inputs:(row5) outputs:()
				// linked node: tMap_1 - inputs:(row5,row1,row2,row6,row3)
				// outputs:(DATASET_PFA)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_ROWS;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row5Struct> getLookup(matchingModeEnum_row5);

				globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);

				/**
				 * [tAdvancedHash_row5 begin ] stop
				 */

				/**
				 * [tFilterColumns_1 begin ] start
				 */

				ok_Hash.put("tFilterColumns_1", false);
				start_Hash.put("tFilterColumns_1", System.currentTimeMillis());

				currentComponent = "tFilterColumns_1";

				int tos_count_tFilterColumns_1 = 0;

				class BytesLimit65535_tFilterColumns_1 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFilterColumns_1().limitLog4jByte();

				int nb_line_tFilterColumns_1 = 0;

				/**
				 * [tFilterColumns_1 begin ] stop
				 */

				/**
				 * [tReplicate_1 begin ] start
				 */

				ok_Hash.put("tReplicate_1", false);
				start_Hash.put("tReplicate_1", System.currentTimeMillis());

				currentComponent = "tReplicate_1";

				int tos_count_tReplicate_1 = 0;

				class BytesLimit65535_tReplicate_1 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tReplicate_1().limitLog4jByte();

				/**
				 * [tReplicate_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1",
						System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				class BytesLimit65535_tFileInputDelimited_1 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFileInputDelimited_1().limitLog4jByte();

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				try {

					Object filename_tFileInputDelimited_1 = "D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/customer.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0
								|| random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/customer.csv",
								"UTF-8", ";", "\n", false, 1, 0, -1, -1, false);
					} catch (java.lang.Exception e) {

						throw e;

					}

					while (fid_tFileInputDelimited_1 != null
							&& fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						customerData = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						customerData = new customerDataStruct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									customerData.Id = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									rowstate_tFileInputDelimited_1
											.setException(ex_tFileInputDelimited_1);
								}

							} else {

								customerData.Id = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							customerData.FirstName = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							customerData.LastName = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							customerData.City = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							customerData.Country = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							customerData.Phone = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							customerData.Fax = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1
										.getException();
							}

						} catch (java.lang.Exception e) {
							whetherReject_tFileInputDelimited_1 = true;

							throw (e);

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */
						// Start of branch "customerData"
						if (customerData != null) {

							/**
							 * [tReplicate_1 main ] start
							 */

							currentComponent = "tReplicate_1";

							FiltrerColums = new FiltrerColumsStruct();

							FiltrerColums.Id = customerData.Id;
							FiltrerColums.FirstName = customerData.FirstName;
							FiltrerColums.LastName = customerData.LastName;
							FiltrerColums.City = customerData.City;
							FiltrerColums.Country = customerData.Country;
							FiltrerColums.Phone = customerData.Phone;
							FiltrerColums.Fax = customerData.Fax;

							tos_count_tReplicate_1++;

							/**
							 * [tReplicate_1 main ] stop
							 */

							/**
							 * [tFilterColumns_1 main ] start
							 */

							currentComponent = "tFilterColumns_1";

							row5.FirstName = FiltrerColums.FirstName;

							row5.LastName = FiltrerColums.LastName;

							row5.City = FiltrerColums.City;

							row5.Country = FiltrerColums.Country;

							nb_line_tFilterColumns_1++;

							tos_count_tFilterColumns_1++;

							/**
							 * [tFilterColumns_1 main ] stop
							 */

							/**
							 * [tAdvancedHash_row5 main ] start
							 */

							currentComponent = "tAdvancedHash_row5";

							row5Struct row5_HashRow = new row5Struct();

							row5_HashRow.FirstName = row5.FirstName;

							row5_HashRow.LastName = row5.LastName;

							row5_HashRow.City = row5.City;

							row5_HashRow.Country = row5.Country;

							tHash_Lookup_row5.put(row5_HashRow);

							tos_count_tAdvancedHash_row5++;

							/**
							 * [tAdvancedHash_row5 main ] stop
							 */

						} // End of branch "customerData"

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/customer.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE",
								fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1",
						System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				ok_Hash.put("tReplicate_1", true);
				end_Hash.put("tReplicate_1", System.currentTimeMillis());

				/**
				 * [tReplicate_1 end ] stop
				 */

				/**
				 * [tFilterColumns_1 end ] start
				 */

				currentComponent = "tFilterColumns_1";

				globalMap.put("tFilterColumns_1_NB_LINE",
						nb_line_tFilterColumns_1);

				ok_Hash.put("tFilterColumns_1", true);
				end_Hash.put("tFilterColumns_1", System.currentTimeMillis());

				/**
				 * [tFilterColumns_1 end ] stop
				 */

				/**
				 * [tAdvancedHash_row5 end ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				tHash_Lookup_row5.endPut();

				ok_Hash.put("tAdvancedHash_row5", true);
				end_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row5 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tReplicate_1 finally ] start
				 */

				currentComponent = "tReplicate_1";

				/**
				 * [tReplicate_1 finally ] stop
				 */

				/**
				 * [tFilterColumns_1 finally ] start
				 */

				currentComponent = "tFilterColumns_1";

				/**
				 * [tFilterColumns_1 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row5 finally ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				/**
				 * [tAdvancedHash_row5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class DATASET_PFAStruct implements
			routines.system.IPersistableRow<DATASET_PFAStruct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer IdProduit;

		public Integer getIdProduit() {
			return this.IdProduit;
		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public Integer SupplierId;

		public Integer getSupplierId() {
			return this.SupplierId;
		}

		public Integer Id_Order;

		public Integer getId_Order() {
			return this.Id_Order;
		}

		public String OrderDate;

		public String getOrderDate() {
			return this.OrderDate;
		}

		public Integer CustomerId;

		public Integer getCustomerId() {
			return this.CustomerId;
		}

		public String customerName;

		public String getCustomerName() {
			return this.customerName;
		}

		public String cusomerLastName;

		public String getCusomerLastName() {
			return this.cusomerLastName;
		}

		public String City;

		public String getCity() {
			return this.City;
		}

		public String Country;

		public String getCountry() {
			return this.Country;
		}

		public String supplierName;

		public String getSupplierName() {
			return this.supplierName;
		}

		public Integer Quantity;

		public Integer getQuantity() {
			return this.Quantity;
		}

		public Float TotalAmount;

		public Float getTotalAmount() {
			return this.TotalAmount;
		}

		public Float UnitPrice;

		public Float getUnitPrice() {
			return this.UnitPrice;
		}

		public String Package;

		public String getPackage() {
			return this.Package;
		}

		public Integer IsDiscontinued;

		public Integer getIsDiscontinued() {
			return this.IsDiscontinued;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime
						* result
						+ ((this.IdProduit == null) ? 0 : this.IdProduit
								.hashCode());

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
			final DATASET_PFAStruct other = (DATASET_PFAStruct) obj;

			if (this.IdProduit == null) {
				if (other.IdProduit != null)
					return false;

			} else if (!this.IdProduit.equals(other.IdProduit))

				return false;

			return true;
		}

		public void copyDataTo(DATASET_PFAStruct other) {

			other.IdProduit = this.IdProduit;
			other.ProductName = this.ProductName;
			other.SupplierId = this.SupplierId;
			other.Id_Order = this.Id_Order;
			other.OrderDate = this.OrderDate;
			other.CustomerId = this.CustomerId;
			other.customerName = this.customerName;
			other.cusomerLastName = this.cusomerLastName;
			other.City = this.City;
			other.Country = this.Country;
			other.supplierName = this.supplierName;
			other.Quantity = this.Quantity;
			other.TotalAmount = this.TotalAmount;
			other.UnitPrice = this.UnitPrice;
			other.Package = this.Package;
			other.IsDiscontinued = this.IsDiscontinued;

		}

		public void copyKeysDataTo(DATASET_PFAStruct other) {

			other.IdProduit = this.IdProduit;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.IdProduit = readInteger(dis);

					this.ProductName = readString(dis);

					this.SupplierId = readInteger(dis);

					this.Id_Order = readInteger(dis);

					this.OrderDate = readString(dis);

					this.CustomerId = readInteger(dis);

					this.customerName = readString(dis);

					this.cusomerLastName = readString(dis);

					this.City = readString(dis);

					this.Country = readString(dis);

					this.supplierName = readString(dis);

					this.Quantity = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalAmount = null;
					} else {
						this.TotalAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.UnitPrice = null;
					} else {
						this.UnitPrice = dis.readFloat();
					}

					this.Package = readString(dis);

					this.IsDiscontinued = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IdProduit, dos);

				// String

				writeString(this.ProductName, dos);

				// Integer

				writeInteger(this.SupplierId, dos);

				// Integer

				writeInteger(this.Id_Order, dos);

				// String

				writeString(this.OrderDate, dos);

				// Integer

				writeInteger(this.CustomerId, dos);

				// String

				writeString(this.customerName, dos);

				// String

				writeString(this.cusomerLastName, dos);

				// String

				writeString(this.City, dos);

				// String

				writeString(this.Country, dos);

				// String

				writeString(this.supplierName, dos);

				// Integer

				writeInteger(this.Quantity, dos);

				// Float

				if (this.TotalAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.TotalAmount);
				}

				// Float

				if (this.UnitPrice == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.UnitPrice);
				}

				// String

				writeString(this.Package, dos);

				// Integer

				writeInteger(this.IsDiscontinued, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IdProduit=" + String.valueOf(IdProduit));
			sb.append(",ProductName=" + ProductName);
			sb.append(",SupplierId=" + String.valueOf(SupplierId));
			sb.append(",Id_Order=" + String.valueOf(Id_Order));
			sb.append(",OrderDate=" + OrderDate);
			sb.append(",CustomerId=" + String.valueOf(CustomerId));
			sb.append(",customerName=" + customerName);
			sb.append(",cusomerLastName=" + cusomerLastName);
			sb.append(",City=" + City);
			sb.append(",Country=" + Country);
			sb.append(",supplierName=" + supplierName);
			sb.append(",Quantity=" + String.valueOf(Quantity));
			sb.append(",TotalAmount=" + String.valueOf(TotalAmount));
			sb.append(",UnitPrice=" + String.valueOf(UnitPrice));
			sb.append(",Package=" + Package);
			sb.append(",IsDiscontinued=" + String.valueOf(IsDiscontinued));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(DATASET_PFAStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IdProduit, other.IdProduit);
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
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public static class row1Struct implements
			routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Id;

		public Integer getId() {
			return this.Id;
		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public Integer SupplierId;

		public Integer getSupplierId() {
			return this.SupplierId;
		}

		public Float UnitPrice;

		public Float getUnitPrice() {
			return this.UnitPrice;
		}

		public String Package;

		public String getPackage() {
			return this.Package;
		}

		public Integer IsDiscontinued;

		public Integer getIsDiscontinued() {
			return this.IsDiscontinued;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Id = readInteger(dis);

					this.ProductName = readString(dis);

					this.SupplierId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.UnitPrice = null;
					} else {
						this.UnitPrice = dis.readFloat();
					}

					this.Package = readString(dis);

					this.IsDiscontinued = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Id, dos);

				// String

				writeString(this.ProductName, dos);

				// Integer

				writeInteger(this.SupplierId, dos);

				// Float

				if (this.UnitPrice == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.UnitPrice);
				}

				// String

				writeString(this.Package, dos);

				// Integer

				writeInteger(this.IsDiscontinued, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",ProductName=" + ProductName);
			sb.append(",SupplierId=" + String.valueOf(SupplierId));
			sb.append(",UnitPrice=" + String.valueOf(UnitPrice));
			sb.append(",Package=" + Package);
			sb.append(",IsDiscontinued=" + String.valueOf(IsDiscontinued));
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
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public static class FilterFieldProductStruct implements
			routines.system.IPersistableRow<FilterFieldProductStruct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Id;

		public Integer getId() {
			return this.Id;
		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public Integer SupplierId;

		public Integer getSupplierId() {
			return this.SupplierId;
		}

		public Float UnitPrice;

		public Float getUnitPrice() {
			return this.UnitPrice;
		}

		public String Package;

		public String getPackage() {
			return this.Package;
		}

		public Integer IsDiscontinued;

		public Integer getIsDiscontinued() {
			return this.IsDiscontinued;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Id = readInteger(dis);

					this.ProductName = readString(dis);

					this.SupplierId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.UnitPrice = null;
					} else {
						this.UnitPrice = dis.readFloat();
					}

					this.Package = readString(dis);

					this.IsDiscontinued = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Id, dos);

				// String

				writeString(this.ProductName, dos);

				// Integer

				writeInteger(this.SupplierId, dos);

				// Float

				if (this.UnitPrice == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.UnitPrice);
				}

				// String

				writeString(this.Package, dos);

				// Integer

				writeInteger(this.IsDiscontinued, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",ProductName=" + ProductName);
			sb.append(",SupplierId=" + String.valueOf(SupplierId));
			sb.append(",UnitPrice=" + String.valueOf(UnitPrice));
			sb.append(",Package=" + Package);
			sb.append(",IsDiscontinued=" + String.valueOf(IsDiscontinued));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(FilterFieldProductStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public static class after_tFileInputDelimited_2Struct implements
			routines.system.IPersistableRow<after_tFileInputDelimited_2Struct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Id;

		public Integer getId() {
			return this.Id;
		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public Integer SupplierId;

		public Integer getSupplierId() {
			return this.SupplierId;
		}

		public Float UnitPrice;

		public Float getUnitPrice() {
			return this.UnitPrice;
		}

		public String Package;

		public String getPackage() {
			return this.Package;
		}

		public Integer IsDiscontinued;

		public Integer getIsDiscontinued() {
			return this.IsDiscontinued;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Id = readInteger(dis);

					this.ProductName = readString(dis);

					this.SupplierId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.UnitPrice = null;
					} else {
						this.UnitPrice = dis.readFloat();
					}

					this.Package = readString(dis);

					this.IsDiscontinued = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Id, dos);

				// String

				writeString(this.ProductName, dos);

				// Integer

				writeInteger(this.SupplierId, dos);

				// Float

				if (this.UnitPrice == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.UnitPrice);
				}

				// String

				writeString(this.Package, dos);

				// Integer

				writeInteger(this.IsDiscontinued, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",ProductName=" + ProductName);
			sb.append(",SupplierId=" + String.valueOf(SupplierId));
			sb.append(",UnitPrice=" + String.valueOf(UnitPrice));
			sb.append(",Package=" + Package);
			sb.append(",IsDiscontinued=" + String.valueOf(IsDiscontinued));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public void tFileInputDelimited_2Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				tFileInputDelimited_1Process(globalMap);
				tFileInputDelimited_3Process(globalMap);
				tFileInputDelimited_4Process(globalMap);
				tFileInputDelimited_5Process(globalMap);

				FilterFieldProductStruct FilterFieldProduct = new FilterFieldProductStruct();
				row1Struct row1 = new row1Struct();
				DATASET_PFAStruct DATASET_PFA = new DATASET_PFAStruct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				int tos_count_tLogRow_1 = 0;

				class BytesLimit65535_tLogRow_1 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tLogRow_1().limitLog4jByte();

				// /////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[16];

					public void addRow(String[] row) {

						for (int i = 0; i < 16; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i],
										row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 15 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 15 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(
									new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|%15$-");
							sbformat.append(colLengths[14]);
							sbformat.append("s");

							sbformat.append("|%16$-");
							sbformat.append(colLengths[15]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(),
									(Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0]
								- fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[13]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[14]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[15]
								- fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "IdProduit",
						"ProductName", "SupplierId", "Id_Order", "OrderDate",
						"CustomerId", "customerName", "cusomerLastName",
						"City", "Country", "supplierName", "Quantity",
						"TotalAmount", "UnitPrice", "Package",
						"IsDiscontinued", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
				// /////////////////////

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				int tos_count_tMap_1 = 0;

				class BytesLimit65535_tMap_1 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tMap_1().limitLog4jByte();

				// ###############################
				// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
						.get("tHash_Lookup_row2"));

				tHash_Lookup_row2.initGet();

				row2Struct row2HashKey = new row2Struct();
				row2Struct row2Default = new row2Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) globalMap
						.get("tHash_Lookup_row3"));

				tHash_Lookup_row3.initGet();

				row3Struct row3HashKey = new row3Struct();
				row3Struct row3Default = new row3Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) globalMap
						.get("tHash_Lookup_row5"));

				tHash_Lookup_row5.initGet();

				row5Struct row5HashKey = new row5Struct();
				row5Struct row5Default = new row5Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) globalMap
						.get("tHash_Lookup_row6"));

				tHash_Lookup_row6.initGet();

				row6Struct row6HashKey = new row6Struct();
				row6Struct row6Default = new row6Struct();
				// ###############################

				// ###############################
				// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
				// ###############################

				// ###############################
				// # Outputs initialization
				DATASET_PFAStruct DATASET_PFA_tmp = new DATASET_PFAStruct();
				// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFilterColumns_2 begin ] start
				 */

				ok_Hash.put("tFilterColumns_2", false);
				start_Hash.put("tFilterColumns_2", System.currentTimeMillis());

				currentComponent = "tFilterColumns_2";

				int tos_count_tFilterColumns_2 = 0;

				class BytesLimit65535_tFilterColumns_2 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFilterColumns_2().limitLog4jByte();

				int nb_line_tFilterColumns_2 = 0;

				/**
				 * [tFilterColumns_2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2",
						System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				class BytesLimit65535_tFileInputDelimited_2 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFileInputDelimited_2().limitLog4jByte();

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				try {

					Object filename_tFileInputDelimited_2 = "D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/product.csv";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0
								|| random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/product.csv",
								"UTF-8", ";", "\n", true, 1, 0, -1, -1, false);
					} catch (java.lang.Exception e) {

						throw e;

					}

					while (fid_tFileInputDelimited_2 != null
							&& fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();

						FilterFieldProduct = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						FilterFieldProduct = new FilterFieldProductStruct();
						try {

							int columnIndexWithD_tFileInputDelimited_2 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_2 = 0;

							temp = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									FilterFieldProduct.Id = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									rowstate_tFileInputDelimited_2
											.setException(ex_tFileInputDelimited_2);
								}

							} else {

								FilterFieldProduct.Id = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 1;

							FilterFieldProduct.ProductName = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 2;

							temp = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									FilterFieldProduct.SupplierId = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									rowstate_tFileInputDelimited_2
											.setException(ex_tFileInputDelimited_2);
								}

							} else {

								FilterFieldProduct.SupplierId = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 3;

							temp = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									FilterFieldProduct.UnitPrice = ParserUtils
											.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									rowstate_tFileInputDelimited_2
											.setException(ex_tFileInputDelimited_2);
								}

							} else {

								FilterFieldProduct.UnitPrice = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 4;

							FilterFieldProduct.Package = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 5;

							temp = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									FilterFieldProduct.IsDiscontinued = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									rowstate_tFileInputDelimited_2
											.setException(ex_tFileInputDelimited_2);
								}

							} else {

								FilterFieldProduct.IsDiscontinued = null;

							}

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2
										.getException();
							}

						} catch (java.lang.Exception e) {
							whetherReject_tFileInputDelimited_2 = true;

							throw (e);

						}

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */
						// Start of branch "FilterFieldProduct"
						if (FilterFieldProduct != null) {

							/**
							 * [tFilterColumns_2 main ] start
							 */

							currentComponent = "tFilterColumns_2";

							row1.Id = FilterFieldProduct.Id;

							row1.ProductName = FilterFieldProduct.ProductName;

							row1.SupplierId = FilterFieldProduct.SupplierId;

							row1.UnitPrice = FilterFieldProduct.UnitPrice;

							row1.Package = FilterFieldProduct.Package;

							row1.IsDiscontinued = FilterFieldProduct.IsDiscontinued;

							nb_line_tFilterColumns_2++;

							tos_count_tFilterColumns_2++;

							/**
							 * [tFilterColumns_2 main ] stop
							 */

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							// /////////////////////////////////////////////
							// Starting Lookup Table "row2"
							// /////////////////////////////////////////////

							boolean forceLooprow2 = false;

							row2Struct row2ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								tHash_Lookup_row2.lookup(row2HashKey);

								if (!tHash_Lookup_row2.hasNext()) { // G_TM_M_090

									forceLooprow2 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							else { // G 20 - G 21
								forceLooprow2 = true;
							} // G 21

							row2Struct row2 = null;

							while ((tHash_Lookup_row2 != null && tHash_Lookup_row2
									.hasNext()) || forceLooprow2) { // G_TM_M_043

								// CALL close loop of lookup 'row2'

								row2Struct fromLookup_row2 = null;
								row2 = row2Default;

								if (!forceLooprow2) { // G 46

									fromLookup_row2 = tHash_Lookup_row2.next();

									if (fromLookup_row2 != null) {
										row2 = fromLookup_row2;
									}

								} // G 46

								forceLooprow2 = false;

								// /////////////////////////////////////////////
								// Starting Lookup Table "row3"
								// /////////////////////////////////////////////

								boolean forceLooprow3 = false;

								row3Struct row3ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

									tHash_Lookup_row3.lookup(row3HashKey);

									if (!tHash_Lookup_row3.hasNext()) { // G_TM_M_090

										forceLooprow3 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								else { // G 20 - G 21
									forceLooprow3 = true;
								} // G 21

								row3Struct row3 = null;

								while ((tHash_Lookup_row3 != null && tHash_Lookup_row3
										.hasNext()) || forceLooprow3) { // G_TM_M_043

									// CALL close loop of lookup 'row3'

									row3Struct fromLookup_row3 = null;
									row3 = row3Default;

									if (!forceLooprow3) { // G 46

										fromLookup_row3 = tHash_Lookup_row3
												.next();

										if (fromLookup_row3 != null) {
											row3 = fromLookup_row3;
										}

									} // G 46

									forceLooprow3 = false;

									// /////////////////////////////////////////////
									// Starting Lookup Table "row5"
									// /////////////////////////////////////////////

									boolean forceLooprow5 = false;

									row5Struct row5ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										tHash_Lookup_row5.lookup(row5HashKey);

										if (!tHash_Lookup_row5.hasNext()) { // G_TM_M_090

											forceLooprow5 = true;

										} // G_TM_M_090

									} // G_TM_M_020

									else { // G 20 - G 21
										forceLooprow5 = true;
									} // G 21

									row5Struct row5 = null;

									while ((tHash_Lookup_row5 != null && tHash_Lookup_row5
											.hasNext()) || forceLooprow5) { // G_TM_M_043

										// CALL close loop of lookup 'row5'

										row5Struct fromLookup_row5 = null;
										row5 = row5Default;

										if (!forceLooprow5) { // G 46

											fromLookup_row5 = tHash_Lookup_row5
													.next();

											if (fromLookup_row5 != null) {
												row5 = fromLookup_row5;
											}

										} // G 46

										forceLooprow5 = false;

										// /////////////////////////////////////////////
										// Starting Lookup Table "row6"
										// /////////////////////////////////////////////

										boolean forceLooprow6 = false;

										row6Struct row6ObjectFromLookup = null;

										if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

											tHash_Lookup_row6
													.lookup(row6HashKey);

											if (!tHash_Lookup_row6.hasNext()) { // G_TM_M_090

												forceLooprow6 = true;

											} // G_TM_M_090

										} // G_TM_M_020

										else { // G 20 - G 21
											forceLooprow6 = true;
										} // G 21

										row6Struct row6 = null;

										while ((tHash_Lookup_row6 != null && tHash_Lookup_row6
												.hasNext()) || forceLooprow6) { // G_TM_M_043

											// CALL close loop of lookup 'row6'

											row6Struct fromLookup_row6 = null;
											row6 = row6Default;

											if (!forceLooprow6) { // G 46

												fromLookup_row6 = tHash_Lookup_row6
														.next();

												if (fromLookup_row6 != null) {
													row6 = fromLookup_row6;
												}

											} // G 46

											forceLooprow6 = false;

											// ###############################
											{ // start of Var scope

												// ###############################
												// # Vars tables

												Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
												// ###############################
												// # Output tables

												DATASET_PFA = null;

												// # Output table :
												// 'DATASET_PFA'
												DATASET_PFA_tmp.IdProduit = row1.Id;
												DATASET_PFA_tmp.ProductName = row1.ProductName;
												DATASET_PFA_tmp.SupplierId = row1.SupplierId;
												DATASET_PFA_tmp.Id_Order = row2.Id;
												DATASET_PFA_tmp.OrderDate = row2.OrderDate;
												DATASET_PFA_tmp.CustomerId = row2.CustomerId;
												DATASET_PFA_tmp.customerName = row5.FirstName;
												DATASET_PFA_tmp.cusomerLastName = row5.LastName;
												DATASET_PFA_tmp.City = row5.City;
												DATASET_PFA_tmp.Country = row5.Country;
												DATASET_PFA_tmp.supplierName = row6.ContactName;
												DATASET_PFA_tmp.Quantity = row3.Quantity;
												DATASET_PFA_tmp.TotalAmount = row2.TotalAmount;
												DATASET_PFA_tmp.UnitPrice = row1.UnitPrice;
												DATASET_PFA_tmp.Package = row1.Package;
												DATASET_PFA_tmp.IsDiscontinued = row1.IsDiscontinued;
												DATASET_PFA = DATASET_PFA_tmp;
												// ###############################

											} // end of Var scope

											rejectedInnerJoin_tMap_1 = false;

											tos_count_tMap_1++;

											/**
											 * [tMap_1 main ] stop
											 */
											// Start of branch "DATASET_PFA"
											if (DATASET_PFA != null) {

												/**
												 * [tLogRow_1 main ] start
												 */

												currentComponent = "tLogRow_1";

												// /////////////////////

												String[] row_tLogRow_1 = new String[16];

												if (DATASET_PFA.IdProduit != null) { //
													row_tLogRow_1[0] = String
															.valueOf(DATASET_PFA.IdProduit);

												} //

												if (DATASET_PFA.ProductName != null) { //
													row_tLogRow_1[1] = String
															.valueOf(DATASET_PFA.ProductName);

												} //

												if (DATASET_PFA.SupplierId != null) { //
													row_tLogRow_1[2] = String
															.valueOf(DATASET_PFA.SupplierId);

												} //

												if (DATASET_PFA.Id_Order != null) { //
													row_tLogRow_1[3] = String
															.valueOf(DATASET_PFA.Id_Order);

												} //

												if (DATASET_PFA.OrderDate != null) { //
													row_tLogRow_1[4] = String
															.valueOf(DATASET_PFA.OrderDate);

												} //

												if (DATASET_PFA.CustomerId != null) { //
													row_tLogRow_1[5] = String
															.valueOf(DATASET_PFA.CustomerId);

												} //

												if (DATASET_PFA.customerName != null) { //
													row_tLogRow_1[6] = String
															.valueOf(DATASET_PFA.customerName);

												} //

												if (DATASET_PFA.cusomerLastName != null) { //
													row_tLogRow_1[7] = String
															.valueOf(DATASET_PFA.cusomerLastName);

												} //

												if (DATASET_PFA.City != null) { //
													row_tLogRow_1[8] = String
															.valueOf(DATASET_PFA.City);

												} //

												if (DATASET_PFA.Country != null) { //
													row_tLogRow_1[9] = String
															.valueOf(DATASET_PFA.Country);

												} //

												if (DATASET_PFA.supplierName != null) { //
													row_tLogRow_1[10] = String
															.valueOf(DATASET_PFA.supplierName);

												} //

												if (DATASET_PFA.Quantity != null) { //
													row_tLogRow_1[11] = String
															.valueOf(DATASET_PFA.Quantity);

												} //

												if (DATASET_PFA.TotalAmount != null) { //
													row_tLogRow_1[12] = FormatterUtils
															.formatUnwithE(DATASET_PFA.TotalAmount);

												} //

												if (DATASET_PFA.UnitPrice != null) { //
													row_tLogRow_1[13] = FormatterUtils
															.formatUnwithE(DATASET_PFA.UnitPrice);

												} //

												if (DATASET_PFA.Package != null) { //
													row_tLogRow_1[14] = String
															.valueOf(DATASET_PFA.Package);

												} //

												if (DATASET_PFA.IsDiscontinued != null) { //
													row_tLogRow_1[15] = String
															.valueOf(DATASET_PFA.IsDiscontinued);

												} //

												util_tLogRow_1
														.addRow(row_tLogRow_1);
												nb_line_tLogRow_1++;
												// ////

												// ////

												// /////////////////////

												tos_count_tLogRow_1++;

												/**
												 * [tLogRow_1 main ] stop
												 */

											} // End of branch "DATASET_PFA"

										} // close loop of lookup 'row6' //
											// G_TM_M_043

									} // close loop of lookup 'row5' //
										// G_TM_M_043

								} // close loop of lookup 'row3' // G_TM_M_043

							} // close loop of lookup 'row2' // G_TM_M_043

						} // End of branch "FilterFieldProduct"

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

					}
				} finally {
					if (!((Object) ("D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/product.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_2 != null) {
							fid_tFileInputDelimited_2.close();
						}
					}
					if (fid_tFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE",
								fid_tFileInputDelimited_2.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2",
						System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tFilterColumns_2 end ] start
				 */

				currentComponent = "tFilterColumns_2";

				globalMap.put("tFilterColumns_2_NB_LINE",
						nb_line_tFilterColumns_2);

				ok_Hash.put("tFilterColumns_2", true);
				end_Hash.put("tFilterColumns_2", System.currentTimeMillis());

				/**
				 * [tFilterColumns_2 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

				// ###############################
				// # Lookup hashes releasing
				if (tHash_Lookup_row2 != null) {
					tHash_Lookup_row2.endGet();
				}
				globalMap.remove("tHash_Lookup_row2");

				if (tHash_Lookup_row3 != null) {
					tHash_Lookup_row3.endGet();
				}
				globalMap.remove("tHash_Lookup_row3");

				if (tHash_Lookup_row5 != null) {
					tHash_Lookup_row5.endGet();
				}
				globalMap.remove("tHash_Lookup_row5");

				if (tHash_Lookup_row6 != null) {
					tHash_Lookup_row6.endGet();
				}
				globalMap.remove("tHash_Lookup_row6");

				// ###############################

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

				// ////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap
							.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(
							new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1
						.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
				// ////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

				// /////////////////////

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row5");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row2");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row6");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row3");

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tFilterColumns_2 finally ] start
				 */

				currentComponent = "tFilterColumns_2";

				/**
				 * [tFilterColumns_2 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements
			routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Id;

		public Integer getId() {
			return this.Id;
		}

		public String OrderDate;

		public String getOrderDate() {
			return this.OrderDate;
		}

		public Integer CustomerId;

		public Integer getCustomerId() {
			return this.CustomerId;
		}

		public Float TotalAmount;

		public Float getTotalAmount() {
			return this.TotalAmount;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Id = readInteger(dis);

					this.OrderDate = readString(dis);

					this.CustomerId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalAmount = null;
					} else {
						this.TotalAmount = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Id, dos);

				// String

				writeString(this.OrderDate, dos);

				// Integer

				writeInteger(this.CustomerId, dos);

				// Float

				if (this.TotalAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.TotalAmount);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",OrderDate=" + OrderDate);
			sb.append(",CustomerId=" + String.valueOf(CustomerId));
			sb.append(",TotalAmount=" + String.valueOf(TotalAmount));
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
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public static class FilterFieldordersStruct implements
			routines.system.IPersistableRow<FilterFieldordersStruct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Id;

		public Integer getId() {
			return this.Id;
		}

		public String OrderDate;

		public String getOrderDate() {
			return this.OrderDate;
		}

		public Integer CustomerId;

		public Integer getCustomerId() {
			return this.CustomerId;
		}

		public Float TotalAmount;

		public Float getTotalAmount() {
			return this.TotalAmount;
		}

		public Integer OrderNumber;

		public Integer getOrderNumber() {
			return this.OrderNumber;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Id = readInteger(dis);

					this.OrderDate = readString(dis);

					this.CustomerId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalAmount = null;
					} else {
						this.TotalAmount = dis.readFloat();
					}

					this.OrderNumber = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Id, dos);

				// String

				writeString(this.OrderDate, dos);

				// Integer

				writeInteger(this.CustomerId, dos);

				// Float

				if (this.TotalAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.TotalAmount);
				}

				// Integer

				writeInteger(this.OrderNumber, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",OrderDate=" + OrderDate);
			sb.append(",CustomerId=" + String.valueOf(CustomerId));
			sb.append(",TotalAmount=" + String.valueOf(TotalAmount));
			sb.append(",OrderNumber=" + String.valueOf(OrderNumber));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(FilterFieldordersStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public void tFileInputDelimited_3Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				FilterFieldordersStruct FilterFieldorders = new FilterFieldordersStruct();
				row2Struct row2 = new row2Struct();

				/**
				 * [tAdvancedHash_row2 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row2", false);
				start_Hash
						.put("tAdvancedHash_row2", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row2";

				int tos_count_tAdvancedHash_row2 = 0;

				class BytesLimit65535_tAdvancedHash_row2 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tAdvancedHash_row2().limitLog4jByte();

				// connection name:row2
				// source node:tFilterColumns_3 - inputs:(FilterFieldorders)
				// outputs:(row2,row2) | target node:tAdvancedHash_row2 -
				// inputs:(row2) outputs:()
				// linked node: tMap_1 - inputs:(row5,row1,row2,row6,row3)
				// outputs:(DATASET_PFA)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_ROWS;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct> getLookup(matchingModeEnum_row2);

				globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);

				/**
				 * [tAdvancedHash_row2 begin ] stop
				 */

				/**
				 * [tFilterColumns_3 begin ] start
				 */

				ok_Hash.put("tFilterColumns_3", false);
				start_Hash.put("tFilterColumns_3", System.currentTimeMillis());

				currentComponent = "tFilterColumns_3";

				int tos_count_tFilterColumns_3 = 0;

				class BytesLimit65535_tFilterColumns_3 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFilterColumns_3().limitLog4jByte();

				int nb_line_tFilterColumns_3 = 0;

				/**
				 * [tFilterColumns_3 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_3", false);
				start_Hash.put("tFileInputDelimited_3",
						System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_3";

				int tos_count_tFileInputDelimited_3 = 0;

				class BytesLimit65535_tFileInputDelimited_3 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFileInputDelimited_3().limitLog4jByte();

				final routines.system.RowState rowstate_tFileInputDelimited_3 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_3 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_3 = null;
				try {

					Object filename_tFileInputDelimited_3 = "D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/orders.csv";
					if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_3 = 0, random_value_tFileInputDelimited_3 = -1;
						if (footer_value_tFileInputDelimited_3 > 0
								|| random_value_tFileInputDelimited_3 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_3 = new org.talend.fileprocess.FileInputDelimited(
								"D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/orders.csv",
								"US-ASCII", ";", "\n", true, 1, 0, -1, -1,
								false);
					} catch (java.lang.Exception e) {

						throw e;

					}

					while (fid_tFileInputDelimited_3 != null
							&& fid_tFileInputDelimited_3.nextRecord()) {
						rowstate_tFileInputDelimited_3.reset();

						FilterFieldorders = null;

						boolean whetherReject_tFileInputDelimited_3 = false;
						FilterFieldorders = new FilterFieldordersStruct();
						try {

							int columnIndexWithD_tFileInputDelimited_3 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_3 = 0;

							temp = fid_tFileInputDelimited_3
									.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									FilterFieldorders.Id = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									rowstate_tFileInputDelimited_3
											.setException(ex_tFileInputDelimited_3);
								}

							} else {

								FilterFieldorders.Id = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 1;

							FilterFieldorders.OrderDate = fid_tFileInputDelimited_3
									.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 2;

							temp = fid_tFileInputDelimited_3
									.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									FilterFieldorders.CustomerId = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									rowstate_tFileInputDelimited_3
											.setException(ex_tFileInputDelimited_3);
								}

							} else {

								FilterFieldorders.CustomerId = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 3;

							temp = fid_tFileInputDelimited_3
									.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									FilterFieldorders.TotalAmount = ParserUtils
											.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									rowstate_tFileInputDelimited_3
											.setException(ex_tFileInputDelimited_3);
								}

							} else {

								FilterFieldorders.TotalAmount = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 4;

							temp = fid_tFileInputDelimited_3
									.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									FilterFieldorders.OrderNumber = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									rowstate_tFileInputDelimited_3
											.setException(ex_tFileInputDelimited_3);
								}

							} else {

								FilterFieldorders.OrderNumber = null;

							}

							if (rowstate_tFileInputDelimited_3.getException() != null) {
								throw rowstate_tFileInputDelimited_3
										.getException();
							}

						} catch (java.lang.Exception e) {
							whetherReject_tFileInputDelimited_3 = true;

							throw (e);

						}

						/**
						 * [tFileInputDelimited_3 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_3 main ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						tos_count_tFileInputDelimited_3++;

						/**
						 * [tFileInputDelimited_3 main ] stop
						 */
						// Start of branch "FilterFieldorders"
						if (FilterFieldorders != null) {

							/**
							 * [tFilterColumns_3 main ] start
							 */

							currentComponent = "tFilterColumns_3";

							row2.Id = FilterFieldorders.Id;

							row2.OrderDate = FilterFieldorders.OrderDate;

							row2.CustomerId = FilterFieldorders.CustomerId;

							row2.TotalAmount = FilterFieldorders.TotalAmount;

							nb_line_tFilterColumns_3++;

							tos_count_tFilterColumns_3++;

							/**
							 * [tFilterColumns_3 main ] stop
							 */

							/**
							 * [tAdvancedHash_row2 main ] start
							 */

							currentComponent = "tAdvancedHash_row2";

							row2Struct row2_HashRow = new row2Struct();

							row2_HashRow.Id = row2.Id;

							row2_HashRow.OrderDate = row2.OrderDate;

							row2_HashRow.CustomerId = row2.CustomerId;

							row2_HashRow.TotalAmount = row2.TotalAmount;

							tHash_Lookup_row2.put(row2_HashRow);

							tos_count_tAdvancedHash_row2++;

							/**
							 * [tAdvancedHash_row2 main ] stop
							 */

						} // End of branch "FilterFieldorders"

						/**
						 * [tFileInputDelimited_3 end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

					}
				} finally {
					if (!((Object) ("D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/orders.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_3 != null) {
							fid_tFileInputDelimited_3.close();
						}
					}
					if (fid_tFileInputDelimited_3 != null) {
						globalMap.put("tFileInputDelimited_3_NB_LINE",
								fid_tFileInputDelimited_3.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_3", true);
				end_Hash.put("tFileInputDelimited_3",
						System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_3 end ] stop
				 */

				/**
				 * [tFilterColumns_3 end ] start
				 */

				currentComponent = "tFilterColumns_3";

				globalMap.put("tFilterColumns_3_NB_LINE",
						nb_line_tFilterColumns_3);

				ok_Hash.put("tFilterColumns_3", true);
				end_Hash.put("tFilterColumns_3", System.currentTimeMillis());

				/**
				 * [tFilterColumns_3 end ] stop
				 */

				/**
				 * [tAdvancedHash_row2 end ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				tHash_Lookup_row2.endPut();

				ok_Hash.put("tAdvancedHash_row2", true);
				end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row2 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_3 finally ] start
				 */

				currentComponent = "tFileInputDelimited_3";

				/**
				 * [tFileInputDelimited_3 finally ] stop
				 */

				/**
				 * [tFilterColumns_3 finally ] start
				 */

				currentComponent = "tFilterColumns_3";

				/**
				 * [tFilterColumns_3 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row2 finally ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				/**
				 * [tAdvancedHash_row2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements
			routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public String ContactName;

		public String getContactName() {
			return this.ContactName;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.ContactName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ContactName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ContactName=" + ContactName);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public static class FilterFieldSupplierStruct implements
			routines.system.IPersistableRow<FilterFieldSupplierStruct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Id;

		public Integer getId() {
			return this.Id;
		}

		public String CompanyName;

		public String getCompanyName() {
			return this.CompanyName;
		}

		public String ContactName;

		public String getContactName() {
			return this.ContactName;
		}

		public String City;

		public String getCity() {
			return this.City;
		}

		public String Country;

		public String getCountry() {
			return this.Country;
		}

		public String Phone;

		public String getPhone() {
			return this.Phone;
		}

		public String Fax;

		public String getFax() {
			return this.Fax;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FORMATION_PROJECT_Projet_PFA.length) {
					if (length < 1024
							&& commonByteArray_FORMATION_PROJECT_Projet_PFA.length == 0) {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[1024];
					} else {
						commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length);
				strReturn = new String(
						commonByteArray_FORMATION_PROJECT_Projet_PFA, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Id = readInteger(dis);

					this.CompanyName = readString(dis);

					this.ContactName = readString(dis);

					this.City = readString(dis);

					this.Country = readString(dis);

					this.Phone = readString(dis);

					this.Fax = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Id, dos);

				// String

				writeString(this.CompanyName, dos);

				// String

				writeString(this.ContactName, dos);

				// String

				writeString(this.City, dos);

				// String

				writeString(this.Country, dos);

				// String

				writeString(this.Phone, dos);

				// String

				writeString(this.Fax, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",CompanyName=" + CompanyName);
			sb.append(",ContactName=" + ContactName);
			sb.append(",City=" + City);
			sb.append(",Country=" + Country);
			sb.append(",Phone=" + Phone);
			sb.append(",Fax=" + Fax);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(FilterFieldSupplierStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public void tFileInputDelimited_4Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				FilterFieldSupplierStruct FilterFieldSupplier = new FilterFieldSupplierStruct();
				row6Struct row6 = new row6Struct();

				/**
				 * [tAdvancedHash_row6 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row6", false);
				start_Hash
						.put("tAdvancedHash_row6", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row6";

				int tos_count_tAdvancedHash_row6 = 0;

				class BytesLimit65535_tAdvancedHash_row6 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tAdvancedHash_row6().limitLog4jByte();

				// connection name:row6
				// source node:tFilterColumns_4 - inputs:(FilterFieldSupplier)
				// outputs:(row6,row6) | target node:tAdvancedHash_row6 -
				// inputs:(row6) outputs:()
				// linked node: tMap_1 - inputs:(row5,row1,row2,row6,row3)
				// outputs:(DATASET_PFA)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row6 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_ROWS;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row6Struct> getLookup(matchingModeEnum_row6);

				globalMap.put("tHash_Lookup_row6", tHash_Lookup_row6);

				/**
				 * [tAdvancedHash_row6 begin ] stop
				 */

				/**
				 * [tFilterColumns_4 begin ] start
				 */

				ok_Hash.put("tFilterColumns_4", false);
				start_Hash.put("tFilterColumns_4", System.currentTimeMillis());

				currentComponent = "tFilterColumns_4";

				int tos_count_tFilterColumns_4 = 0;

				class BytesLimit65535_tFilterColumns_4 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFilterColumns_4().limitLog4jByte();

				int nb_line_tFilterColumns_4 = 0;

				/**
				 * [tFilterColumns_4 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_4 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_4", false);
				start_Hash.put("tFileInputDelimited_4",
						System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_4";

				int tos_count_tFileInputDelimited_4 = 0;

				class BytesLimit65535_tFileInputDelimited_4 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFileInputDelimited_4().limitLog4jByte();

				final routines.system.RowState rowstate_tFileInputDelimited_4 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_4 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4 = null;
				try {

					Object filename_tFileInputDelimited_4 = "D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/supplier.csv";
					if (filename_tFileInputDelimited_4 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_4 = 0, random_value_tFileInputDelimited_4 = -1;
						if (footer_value_tFileInputDelimited_4 > 0
								|| random_value_tFileInputDelimited_4 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_4 = new org.talend.fileprocess.FileInputDelimited(
								"D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/supplier.csv",
								"UTF-8", ";", "\n", true, 1, 0, -1, -1, false);
					} catch (java.lang.Exception e) {

						throw e;

					}

					while (fid_tFileInputDelimited_4 != null
							&& fid_tFileInputDelimited_4.nextRecord()) {
						rowstate_tFileInputDelimited_4.reset();

						FilterFieldSupplier = null;

						boolean whetherReject_tFileInputDelimited_4 = false;
						FilterFieldSupplier = new FilterFieldSupplierStruct();
						try {

							int columnIndexWithD_tFileInputDelimited_4 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_4 = 0;

							temp = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									FilterFieldSupplier.Id = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									rowstate_tFileInputDelimited_4
											.setException(ex_tFileInputDelimited_4);
								}

							} else {

								FilterFieldSupplier.Id = null;

							}

							columnIndexWithD_tFileInputDelimited_4 = 1;

							FilterFieldSupplier.CompanyName = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 2;

							FilterFieldSupplier.ContactName = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 3;

							FilterFieldSupplier.City = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 4;

							FilterFieldSupplier.Country = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 5;

							FilterFieldSupplier.Phone = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 6;

							FilterFieldSupplier.Fax = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							if (rowstate_tFileInputDelimited_4.getException() != null) {
								throw rowstate_tFileInputDelimited_4
										.getException();
							}

						} catch (java.lang.Exception e) {
							whetherReject_tFileInputDelimited_4 = true;

							throw (e);

						}

						/**
						 * [tFileInputDelimited_4 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_4 main ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						tos_count_tFileInputDelimited_4++;

						/**
						 * [tFileInputDelimited_4 main ] stop
						 */
						// Start of branch "FilterFieldSupplier"
						if (FilterFieldSupplier != null) {

							/**
							 * [tFilterColumns_4 main ] start
							 */

							currentComponent = "tFilterColumns_4";

							row6.ContactName = FilterFieldSupplier.ContactName;

							nb_line_tFilterColumns_4++;

							tos_count_tFilterColumns_4++;

							/**
							 * [tFilterColumns_4 main ] stop
							 */

							/**
							 * [tAdvancedHash_row6 main ] start
							 */

							currentComponent = "tAdvancedHash_row6";

							row6Struct row6_HashRow = new row6Struct();

							row6_HashRow.ContactName = row6.ContactName;

							tHash_Lookup_row6.put(row6_HashRow);

							tos_count_tAdvancedHash_row6++;

							/**
							 * [tAdvancedHash_row6 main ] stop
							 */

						} // End of branch "FilterFieldSupplier"

						/**
						 * [tFileInputDelimited_4 end ] start
						 */

						currentComponent = "tFileInputDelimited_4";

					}
				} finally {
					if (!((Object) ("D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/supplier.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_4 != null) {
							fid_tFileInputDelimited_4.close();
						}
					}
					if (fid_tFileInputDelimited_4 != null) {
						globalMap.put("tFileInputDelimited_4_NB_LINE",
								fid_tFileInputDelimited_4.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_4", true);
				end_Hash.put("tFileInputDelimited_4",
						System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_4 end ] stop
				 */

				/**
				 * [tFilterColumns_4 end ] start
				 */

				currentComponent = "tFilterColumns_4";

				globalMap.put("tFilterColumns_4_NB_LINE",
						nb_line_tFilterColumns_4);

				ok_Hash.put("tFilterColumns_4", true);
				end_Hash.put("tFilterColumns_4", System.currentTimeMillis());

				/**
				 * [tFilterColumns_4 end ] stop
				 */

				/**
				 * [tAdvancedHash_row6 end ] start
				 */

				currentComponent = "tAdvancedHash_row6";

				tHash_Lookup_row6.endPut();

				ok_Hash.put("tAdvancedHash_row6", true);
				end_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row6 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_4 finally ] start
				 */

				currentComponent = "tFileInputDelimited_4";

				/**
				 * [tFileInputDelimited_4 finally ] stop
				 */

				/**
				 * [tFilterColumns_4 finally ] start
				 */

				currentComponent = "tFilterColumns_4";

				/**
				 * [tFilterColumns_4 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row6 finally ] start
				 */

				currentComponent = "tAdvancedHash_row6";

				/**
				 * [tAdvancedHash_row6 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 1);
	}

	public static class row3Struct implements
			routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Quantity;

		public Integer getQuantity() {
			return this.Quantity;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Quantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Quantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Quantity=" + String.valueOf(Quantity));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public static class row4Struct implements
			routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_FORMATION_PROJECT_Projet_PFA = new byte[0];
		static byte[] commonByteArray_FORMATION_PROJECT_Projet_PFA = new byte[0];

		public Integer Id;

		public Integer getId() {
			return this.Id;
		}

		public Integer OrderId;

		public Integer getOrderId() {
			return this.OrderId;
		}

		public Integer ProductId;

		public Integer getProductId() {
			return this.ProductId;
		}

		public Float UnitPrice;

		public Float getUnitPrice() {
			return this.UnitPrice;
		}

		public Integer Quantity;

		public Integer getQuantity() {
			return this.Quantity;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FORMATION_PROJECT_Projet_PFA) {

				try {

					int length = 0;

					this.Id = readInteger(dis);

					this.OrderId = readInteger(dis);

					this.ProductId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.UnitPrice = null;
					} else {
						this.UnitPrice = dis.readFloat();
					}

					this.Quantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Id, dos);

				// Integer

				writeInteger(this.OrderId, dos);

				// Integer

				writeInteger(this.ProductId, dos);

				// Float

				if (this.UnitPrice == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.UnitPrice);
				}

				// Integer

				writeInteger(this.Quantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",OrderId=" + String.valueOf(OrderId));
			sb.append(",ProductId=" + String.valueOf(ProductId));
			sb.append(",UnitPrice=" + String.valueOf(UnitPrice));
			sb.append(",Quantity=" + String.valueOf(Quantity));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
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

	public void tFileInputDelimited_5Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tFileInputDelimited_5_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				row4Struct row4 = new row4Struct();
				row3Struct row3 = new row3Struct();

				/**
				 * [tAdvancedHash_row3 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row3", false);
				start_Hash
						.put("tAdvancedHash_row3", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row3";

				int tos_count_tAdvancedHash_row3 = 0;

				class BytesLimit65535_tAdvancedHash_row3 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tAdvancedHash_row3().limitLog4jByte();

				// connection name:row3
				// source node:tFilterColumns_5 - inputs:(row4)
				// outputs:(row3,row3) | target node:tAdvancedHash_row3 -
				// inputs:(row3) outputs:()
				// linked node: tMap_1 - inputs:(row5,row1,row2,row6,row3)
				// outputs:(DATASET_PFA)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_ROWS;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row3Struct> getLookup(matchingModeEnum_row3);

				globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);

				/**
				 * [tAdvancedHash_row3 begin ] stop
				 */

				/**
				 * [tFilterColumns_5 begin ] start
				 */

				ok_Hash.put("tFilterColumns_5", false);
				start_Hash.put("tFilterColumns_5", System.currentTimeMillis());

				currentComponent = "tFilterColumns_5";

				int tos_count_tFilterColumns_5 = 0;

				class BytesLimit65535_tFilterColumns_5 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFilterColumns_5().limitLog4jByte();

				int nb_line_tFilterColumns_5 = 0;

				/**
				 * [tFilterColumns_5 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_5 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_5", false);
				start_Hash.put("tFileInputDelimited_5",
						System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_5";

				int tos_count_tFileInputDelimited_5 = 0;

				class BytesLimit65535_tFileInputDelimited_5 {
					public void limitLog4jByte() throws Exception {

					}
				}

				new BytesLimit65535_tFileInputDelimited_5().limitLog4jByte();

				final routines.system.RowState rowstate_tFileInputDelimited_5 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_5 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_5 = null;
				try {

					Object filename_tFileInputDelimited_5 = "D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/orderitem.csv";
					if (filename_tFileInputDelimited_5 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_5 = 0, random_value_tFileInputDelimited_5 = -1;
						if (footer_value_tFileInputDelimited_5 > 0
								|| random_value_tFileInputDelimited_5 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_5 = new org.talend.fileprocess.FileInputDelimited(
								"D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/orderitem.csv",
								"US-ASCII", ";", "\n", true, 1, 0, -1, -1,
								false);
					} catch (java.lang.Exception e) {

						throw e;

					}

					while (fid_tFileInputDelimited_5 != null
							&& fid_tFileInputDelimited_5.nextRecord()) {
						rowstate_tFileInputDelimited_5.reset();

						row4 = null;

						boolean whetherReject_tFileInputDelimited_5 = false;
						row4 = new row4Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_5 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_5 = 0;

							temp = fid_tFileInputDelimited_5
									.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row4.Id = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									rowstate_tFileInputDelimited_5
											.setException(ex_tFileInputDelimited_5);
								}

							} else {

								row4.Id = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 1;

							temp = fid_tFileInputDelimited_5
									.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row4.OrderId = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									rowstate_tFileInputDelimited_5
											.setException(ex_tFileInputDelimited_5);
								}

							} else {

								row4.OrderId = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 2;

							temp = fid_tFileInputDelimited_5
									.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row4.ProductId = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									rowstate_tFileInputDelimited_5
											.setException(ex_tFileInputDelimited_5);
								}

							} else {

								row4.ProductId = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 3;

							temp = fid_tFileInputDelimited_5
									.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row4.UnitPrice = ParserUtils
											.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									rowstate_tFileInputDelimited_5
											.setException(ex_tFileInputDelimited_5);
								}

							} else {

								row4.UnitPrice = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 4;

							temp = fid_tFileInputDelimited_5
									.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row4.Quantity = ParserUtils
											.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									rowstate_tFileInputDelimited_5
											.setException(ex_tFileInputDelimited_5);
								}

							} else {

								row4.Quantity = null;

							}

							if (rowstate_tFileInputDelimited_5.getException() != null) {
								throw rowstate_tFileInputDelimited_5
										.getException();
							}

						} catch (java.lang.Exception e) {
							whetherReject_tFileInputDelimited_5 = true;

							throw (e);

						}

						/**
						 * [tFileInputDelimited_5 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_5 main ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						tos_count_tFileInputDelimited_5++;

						/**
						 * [tFileInputDelimited_5 main ] stop
						 */
						// Start of branch "row4"
						if (row4 != null) {

							/**
							 * [tFilterColumns_5 main ] start
							 */

							currentComponent = "tFilterColumns_5";

							row3.Quantity = row4.Quantity;

							nb_line_tFilterColumns_5++;

							tos_count_tFilterColumns_5++;

							/**
							 * [tFilterColumns_5 main ] stop
							 */

							/**
							 * [tAdvancedHash_row3 main ] start
							 */

							currentComponent = "tAdvancedHash_row3";

							row3Struct row3_HashRow = new row3Struct();

							row3_HashRow.Quantity = row3.Quantity;

							tHash_Lookup_row3.put(row3_HashRow);

							tos_count_tAdvancedHash_row3++;

							/**
							 * [tAdvancedHash_row3 main ] stop
							 */

						} // End of branch "row4"

						/**
						 * [tFileInputDelimited_5 end ] start
						 */

						currentComponent = "tFileInputDelimited_5";

					}
				} finally {
					if (!((Object) ("D:/3eme_annee_esisa/Business-Intelligence/Fichier-csv/orderitem.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_5 != null) {
							fid_tFileInputDelimited_5.close();
						}
					}
					if (fid_tFileInputDelimited_5 != null) {
						globalMap.put("tFileInputDelimited_5_NB_LINE",
								fid_tFileInputDelimited_5.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_5", true);
				end_Hash.put("tFileInputDelimited_5",
						System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_5 end ] stop
				 */

				/**
				 * [tFilterColumns_5 end ] start
				 */

				currentComponent = "tFilterColumns_5";

				globalMap.put("tFilterColumns_5_NB_LINE",
						nb_line_tFilterColumns_5);

				ok_Hash.put("tFilterColumns_5", true);
				end_Hash.put("tFilterColumns_5", System.currentTimeMillis());

				/**
				 * [tFilterColumns_5 end ] stop
				 */

				/**
				 * [tAdvancedHash_row3 end ] start
				 */

				currentComponent = "tAdvancedHash_row3";

				tHash_Lookup_row3.endPut();

				ok_Hash.put("tAdvancedHash_row3", true);
				end_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row3 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_5 finally ] start
				 */

				currentComponent = "tFileInputDelimited_5";

				/**
				 * [tFileInputDelimited_5 finally ] stop
				 */

				/**
				 * [tFilterColumns_5 finally ] start
				 */

				currentComponent = "tFilterColumns_5";

				/**
				 * [tFilterColumns_5 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row3 finally ] start
				 */

				currentComponent = "tAdvancedHash_row3";

				/**
				 * [tAdvancedHash_row3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_5_SUBPROCESS_STATE", 1);
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

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	private PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Projet_PFA Projet_PFAClass = new Projet_PFA();

		int exitCode = Projet_PFAClass.runJobInTOS(args);

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

		try {
			// call job/subjob with an existing context, like:
			// --context=production. if without this parameter, there will use
			// the default context instead.
			java.io.InputStream inContext = Projet_PFA.class.getClassLoader()
					.getResourceAsStream(
							"formation_project/projet_pfa_0_1/contexts/"
									+ contextStr + ".properties");
			if (isDefaultContext && inContext == null) {

			} else {
				if (inContext != null) {
					// defaultProps is in order to keep the original context
					// value
					defaultProps.load(inContext);
					inContext.close();
					context = new ContextProperties(defaultProps);
				} else {
					// print info and job continue to run, for case:
					// context_param is not empty.
					System.err.println("Could not find the context "
							+ contextStr);
				}
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param
							.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil
				.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName,
				jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName,
				parent_part_launcher, Thread.currentThread().getId() + "", "",
				"", "", "",
				resumeUtil.convertToJsonText(context, parametersToEncrypt));

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_2) {
			globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_2.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory)
					+ " bytes memory increase when running : Projet_PFA");
		}

		int returnCode = 0;
		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher,
				Thread.currentThread().getId() + "", "", "" + returnCode, "",
				"", "");

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
					context_param.setContextType(keyValue.substring(0, index),
							keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index),
							keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		}

	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" },
			{ "\\'", "\'" }, { "\\r", "\r" }, { "\\f", "\f" }, { "\\b", "\b" },
			{ "\\t", "\t" } };

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

					result.append(keyValue.substring(currIndex,
							index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left
			// into the result
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
 * 179638 characters generated by Talend Open Studio for Data Integration on the
 * 17 avril 2024 18:09:35 CEST
 ************************************************************************************************/
