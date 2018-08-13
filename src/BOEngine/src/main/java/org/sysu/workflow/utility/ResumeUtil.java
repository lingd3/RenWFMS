package org.sysu.workflow.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.sysu.renCommon.dto.ReturnModel;
import org.sysu.workflow.restful.EngineController;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResumeUtil {
	
	public static void resumeBOEngine() {
		String rtidList = getRtidsFromNS();
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<String> rtids = mapper.readValue(rtidList, List.class);
			for (String rtid : rtids) {
				ReturnModel returnModel = new EngineController().Resume(rtid);
				System.out.println(returnModel.getReturnElement().getData());
			}
		} catch (Exception e) {
			System.out.println("Init Resuming");
		}
	}

	public static String getRtidsFromNS() {
		String reqUrl = "http://rennameservice:10234/ns/getRtids"; //NS Address
		String result = "";
        BufferedReader in = null;
		try {
			URL url = new URL(reqUrl);
			URLConnection connection = url.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
		} catch (Exception e) {
//			System.out.println("Get Rtids From NS Exception");
		} finally {
			try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		return result;
	}
}
