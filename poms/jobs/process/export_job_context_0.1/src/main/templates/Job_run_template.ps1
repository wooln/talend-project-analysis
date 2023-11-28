$fileDir = Split-Path -Parent $MyInvocation.MyCommand.Path
cd $fileDir
java '-Dtalend.component.manager.m2.repository=%cd%/../lib' '-Xms256M' '-Xmx1024M' '-Dfile.encoding=UTF-8' -cp '.;../lib/routines.jar;../lib/log4j-slf4j-impl-2.13.2.jar;../lib/log4j-api-2.13.2.jar;../lib/log4j-core-2.13.2.jar;../lib/log4j-1.2-api-2.13.2.jar;../lib/SparseBitSet-1.2.jar;../lib/commons-math3-3.6.1.jar;../lib/commons-collections4-4.4.jar;../lib/jboss-marshalling-2.0.12.Final.jar;../lib/jaxen-1.1.6.jar;../lib/commons-compress-1.21.jar;../lib/commons-codec-1.14.jar;../lib/curvesapi-1.06.jar;../lib/commons-lang3-3.10.jar;../lib/xpathutil-1.0.0.jar;../lib/poi-4.1.2-20200903124306_modified_talend.jar;../lib/dom4j-2.1.3.jar;../lib/jakarta-oro-2.0.8.jar;../lib/poi-ooxml-4.1.2-20200903124306_modified_talend.jar;../lib/slf4j-api-1.7.29.jar;../lib/poi-ooxml-schemas-4.1.2-20200903124306_modified_talend.jar;../lib/xmlbeans-3.1.0.jar;../lib/poi-scratchpad-4.1.2-20200903124306_modified_talend.jar;../lib/talendExcel-1.12-20210908.jar;../lib/crypto-utils-0.31.12.jar;export_job_context_0_1.jar;' export_talend7_context.export_job_context_0_1.export_job_context $args
