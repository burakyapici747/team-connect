#!/bin/bash

# Couchbase'in hazır olmasını bekle
until curl -f http://localhost:8091; do
    echo "Couchbase henüz hazır değil - bekleniyor..."
    sleep 3
done

echo "Couchbase hazır, init işlemleri başlıyor..."

# Cluster'ın durumunu daha detaylı kontrol et
init_status=$(curl -s -u $COUCHBASE_ADMINISTRATOR_USERNAME:$COUCHBASE_ADMINISTRATOR_PASSWORD http://localhost:8091/pools/default)
if [ $? -eq 0 ] && ! echo "$init_status" | grep -q "unknown pool"; then
    echo "Cluster zaten initialize edilmiş durumda. Ayarları güncelleniyor..."
    
    # Mevcut cluster ayarlarını güncelle
    couchbase-cli setting-cluster \
      --cluster localhost \
      --username $COUCHBASE_ADMINISTRATOR_USERNAME \
      --password $COUCHBASE_ADMINISTRATOR_PASSWORD \
      --cluster-ramsize 512 \
      --cluster-index-ramsize 256
else
    echo "Cluster initialize ediliyor..."
    # Cluster'ı başlat
    couchbase-cli cluster-init \
      --cluster-name teamconnect \
      --cluster-username $COUCHBASE_ADMINISTRATOR_USERNAME \
      --cluster-password $COUCHBASE_ADMINISTRATOR_PASSWORD \
      --services data,index,query \
      --cluster-ramsize 512 \
      --cluster-index-ramsize 256
fi

# Bucket işlemleri için biraz bekle
sleep 5

# Bucket'ın var olup olmadığını kontrol et
bucket_exists=$(curl -s -u $COUCHBASE_ADMINISTRATOR_USERNAME:$COUCHBASE_ADMINISTRATOR_PASSWORD http://localhost:8091/pools/default/buckets/teamconnect)
if echo "$bucket_exists" | grep -q "\"name\":\"teamconnect\""; then
    echo "Bucket zaten mevcut, oluşturma adımı atlanıyor..."
else
    echo "Bucket oluşturuluyor..."
    # Bucket oluştur
    couchbase-cli bucket-create \
      --cluster localhost \
      --username $COUCHBASE_ADMINISTRATOR_USERNAME \
      --password $COUCHBASE_ADMINISTRATOR_PASSWORD \
      --bucket teamconnect \
      --bucket-type couchbase \
      --bucket-ramsize 256
fi