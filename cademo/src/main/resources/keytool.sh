#生成服务端keystore
keytool -genkey -validity 3650 -alias server -keyalg RSA -keystore server.keystore -ext san=dns:www.dafy.com,ip:127.0.0.1
#生成客户端keystore
keytool -genkey -validity 3650 -alias client -keyalg RSA -storetype PKCS12 -keystore client.p12

#将客户端证书安装到操作系统中

#导出服务端证书
keytool -export -keystore server.keystore -alias server -file server.cer -rfc
#导出客户端证书
keytool -export -keystore client.p12 -storetype PKCS12 -alias client -file client.cer

#将服务端证书导入到客户端的信任库中
keytool -import -alias server -file server.cer -keystore client.p12
#将客户端证书导入到服务端的信任库中
keytool -import -alias client -file client.cer -keystore server.keystore

#查看keystore证书列表
keytool -list -v -rfc -keystore /tmp/dafy.keystore
keytool -list -keystore jason.keystore

#生成带拓展域名证书
keytool -genkey -validity 3650 -alias www.dafy.com -keyalg RSA -keystore /tmp/dafy.keystore -ext san=dns:www.dafy.com,ip:127.0.0.1