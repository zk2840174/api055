package org.zerock.api01;



import com.google.gson.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootTest
@Log4j2
public class CrewlingTests {

    @Test
    public void test1() throws Exception {

        String url ="https://www.lottecinema.co.kr/LCAPI/Home/getMovie";

        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36";

        String payload ="{\"channelType\":\"HO\",\"osType\":\"W\",\"osVersion\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36\",\"multiLanguageId\":\"KR\",\"data\":{\"memberNoOn\":\"0\"}}";

        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = payload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {

            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            //System.out.println(response.toString());
            JsonElement jsonElement = JsonParser.parseString(response.toString());

            //log.info(jsonElement);

            JsonObject jsonObject  = jsonElement.getAsJsonObject();

            JsonArray movies = jsonObject.getAsJsonObject("Movies").getAsJsonArray("Items").get(0).getAsJsonObject().getAsJsonArray("Items");

            log.info(movies);

            log.info(jsonObject.getAsJsonArray("Items"));



        }catch(Exception ee) {
            ee.printStackTrace();
        }
    }
}
