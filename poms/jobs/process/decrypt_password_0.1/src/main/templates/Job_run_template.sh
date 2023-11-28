#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Dtalend.component.manager.m2.repository=$ROOT_PATH/../lib -Xms256M -Xmx1024M -cp .:$ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/talend-utility.pom:$ROOT_PATH/../lib/commons-codec-1.15.jar:$ROOT_PATH/../lib/commons-lang3-3.10.jar:$ROOT_PATH/../lib/daikon-0.31.12.jar:$ROOT_PATH/../lib/log4j-slf4j-impl-2.13.2.jar:$ROOT_PATH/../lib/log4j-api-2.13.2.jar:$ROOT_PATH/../lib/log4j-core-2.13.2.jar:$ROOT_PATH/../lib/jboss-marshalling-2.0.12.Final.jar:$ROOT_PATH/../lib/dom4j-2.1.3.jar:$ROOT_PATH/../lib/slf4j-api-1.7.29.jar:$ROOT_PATH/../lib/crypto-utils-0.31.12.jar:$ROOT_PATH/decrypt_password_0_1.jar: export_talend7_context.decrypt_password_0_1.decrypt_password --context=Default "$@"
