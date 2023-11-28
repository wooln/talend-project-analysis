%~d0
cd %~dp0
java -Dtalend.component.manager.m2.repository="%cd%/../lib" -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/talend-utility.pom;../lib/commons-codec-1.15.jar;../lib/commons-lang3-3.10.jar;../lib/daikon-0.31.12.jar;../lib/log4j-slf4j-impl-2.13.2.jar;../lib/log4j-api-2.13.2.jar;../lib/log4j-core-2.13.2.jar;../lib/jboss-marshalling-2.0.12.Final.jar;../lib/dom4j-2.1.3.jar;../lib/slf4j-api-1.7.29.jar;../lib/crypto-utils-0.31.12.jar;decrypt_password_0_1.jar; export_talend7_context.decrypt_password_0_1.decrypt_password --context=Default %*
