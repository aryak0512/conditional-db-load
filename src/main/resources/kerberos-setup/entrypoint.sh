#!/bin/bash

REALM=LOCALDEV.COM
KRB5_CONFIG=/etc/krb5.conf
KDC_CONFIG=/etc/krb5kdc/kdc.conf
DB_PATH=/var/lib/krb5kdc/principal

echo "[libdefaults]
    default_realm = $REALM
[realms]
    $REALM = {
        kdc = localhost
        admin_server = localhost
    }
[domain_realm]
    .localdev.com = $REALM
    localdev.com = $REALM" > $KRB5_CONFIG

echo "[kdcdefaults]
    kdc_ports = 88
[realms]
    $REALM = {
        database_name = $DB_PATH
        admin_keytab = /etc/krb5kdc/kadm5.keytab
        acl_file = /etc/krb5kdc/kadm5.acl
        key_stash_file = /etc/krb5kdc/.k5.$REALM
        max_life = 10h 0m 0s
        max_renewable_life = 7d 0h 0m 0s
        master_key_type = aes256-cts
        supported_enctypes = aes256-cts:normal
    }" > $KDC_CONFIG

krb5_newrealm <<EOF
adminpassword
adminpassword
EOF

echo "*/admin *" > /etc/krb5kdc/kadm5.acl

# Add a test principal
kadmin.local -q "addprinc -pw password user1"

krb5kdc &
kadmind &

tail -f /dev/null
