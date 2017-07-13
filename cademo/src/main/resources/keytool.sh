#生成keystore
keytool -genkey -validity 3650 -alias www.dafy.com -keyalg RSA -keystore /tmp/dafy.keystore

#导出证书
keytool -export -keystore /tmp/dafy.keystore -alias www.dafy.com -file /tmp/dafy.cer -rfc

#导入证书到密钥库(用于双向ssl创建信任库)
keytool -import -alias www.dafy.com -file /tmp/dafy.cer -keystore /tmp/dafy.keystore

#查看keystore证书列表
keytool -list -v -rfc -keystore /tmp/dafy.keystore
keytool -list -keystore jason.keystore

#生成带拓展域名证书
keytool -genkey -validity 3650 -alias www.dafy.com -keyalg RSA -keystore /tmp/dafy.keystore -ext san=dns:www.dafy.com,ip:127.0.0.1

keytool -genkey -validity 3650 -alias jason -keyalg RSA -keystore /tmp/jason.keystore -ext san=dns:www.jason.com,ip:127.0.0.1

keytool -genkey -alias jason -keyalg RSA -storetype PKCS12 -keystore /tmp/jason.key.p12
keytool -export -keystore jason.key.p12 -storetype PKCS12 -alias jason -file jason.cer