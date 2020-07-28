package ru.cti.omiliatest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.net.ssl.*;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class HttpsConnectPostURL {

    // Create a trust manager that does not validate certificate chains
    private static TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }};

    // Ignore differences between given hostname and certificate hostname
    private static HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) { return true; }
    };


    public static String sendPOST(String url, String gson) throws IOException {
        URL obj = new URL(url);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) obj.openConnection();
        httpsURLConnection.setRequestMethod("POST");
        //блок строк для обхода сертификата
//        httpsURLConnection.setSSLSocketFactory(sslsocketfactoryOne);
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            //после обхода сертификата

            // POST - Send request
            httpsURLConnection.setDoOutput(true);
            OutputStream os = httpsURLConnection.getOutputStream();
            String post = gson;
            os.write(gson.getBytes());
            os.flush();
            os.close();

            // POST  - get response
            int responseCode = httpsURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                String result = response.toString();
                GsonConvertation.extractUserFromGson(result);
                return result;
            } else {
                System.out.println("POST request not worked");
            }
            return "Connection failed.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Connection failed.";
    }
}