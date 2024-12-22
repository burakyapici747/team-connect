#!/bin/bash

# Couchbase'in hazır olmasını bekle
sleep 15

# Cluster'ı başlat
couchbase-cli cluster-init \
  --cluster-name teamconnect \
  --cluster-username root \
  --cluster-password plplpl123 \
  --services data,index,query \
  --cluster-ramsize 512 \
  --cluster-index-ramsize 256

# Bucket oluştur
couchbase-cli bucket-create \
  --cluster localhost \
  --username root \
  --password plplpl123 \
  --bucket your_bucket_name \
  --bucket-type couchbase \
  --bucket-ramsize 256 