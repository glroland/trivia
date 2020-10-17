#!/bin/bash

# gather command line parameters
OCP_SERVICE=$1
if [ -z "$OCP_SERVICE" ]
then
	echo "Usage: ocproute.sh <service_name>"
	exit
fi

# get URLs for each service
OCP_ROUTEDUMP=$(oc get routes -o go-template='{{range .items}}{{.spec.to.name}},{{if .spec.tls}}https{{else}}http{{end}}://{{.spec.host}}{{"\n"}}{{end}}' | grep $OCP_SERVICE)

# extract the route for the resulting service
OCP_ROUTE=$(echo "$OCP_ROUTEDUMP" | sed -e 's/.*,//g')

echo $OCP_ROUTE


