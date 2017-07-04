#1.1生成根证书key
openssl genrsa -out DFCA.key
#1.2生成根证书的csr
openssl req -new -key DFCA.key -out DFCA.csr
#1.3生成根证书
openssl x509 -req -days 3650 -in DFCA.csr -signkey DFCA.key -out DFCA.crt

#2.1生成服务端证书的key
openssl genrsa -out server.key
#2.2生成服务器生疏的csr
openssl req -new -key server.key -out server.csr
#2.3生成服务器证书
openssl ca -in server.csr -cert DFCA.crt -keyfile DFCA.key -out server.crt -days 365

#3.1创建客户端证书key
openssl genrsa -des3 -out client.key 1024
#3.2创建客户端证书csr
openssl req -new -key client.key -out client.csr
#3.3创建客户端证书crt
openssl ca -in client.csr -cert DFCA.crt -keyfile DFCA.key -out client.crt -days 365