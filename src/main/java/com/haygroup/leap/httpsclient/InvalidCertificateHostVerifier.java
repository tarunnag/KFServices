package com.haygroup.leap.httpsclient;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class InvalidCertificateHostVerifier implements HostnameVerifier{
@Override
public boolean verify(String paramString, SSLSession paramSSLSession) {
  return true;
}

}