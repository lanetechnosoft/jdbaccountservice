package la.com.jdbbank.service.jdbaccountservice.entities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeeList {
    public static final Gson gson = new Gson();

    public static final JsonObject feeList =gson.fromJson("{\n" +
            "  \"LAK\": [\n" +
            "    {\n" +
            "      \"from\": 0,\n" +
            "      \"feeamount\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 2000001,\n" +
            "      \"feeamount\": 1500\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 3000001,\n" +
            "      \"feeamount\": 2500\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 4000001,\n" +
            "      \"feeamount\": 3000\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 5000001,\n" +
            "      \"feeamount\": 4500\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 7000001,\n" +
            "      \"feeamount\": 7500\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 10000001,\n" +
            "      \"feeamount\": 12000\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 30000001,\n" +
            "      \"feeamount\": 15500\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 50000001,\n" +
            "      \"feeamount\": 20000\n" +
            "    }\n" +
            "  ],\n" +
            "  \"THB\": [\n" +
            "    {\n" +
            "      \"from\": 0,\n" +
            "      \"feeamount\": 5\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 101,\n" +
            "      \"feeamount\": 10\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 301,\n" +
            "      \"feeamount\": 20\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 401,\n" +
            "      \"feeamount\": 30\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 501,\n" +
            "      \"feeamount\": 40\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 701,\n" +
            "      \"feeamount\": 30\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 1001,\n" +
            "      \"feeamount\": 50\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 3001,\n" +
            "      \"feeamount\": 60\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 5001,\n" +
            "      \"feeamount\": 70\n" +
            "    }\n" +
            "  ],\n" +
            "  \"USD\": [\n" +
            "    {\n" +
            "      \"from\": 0,\n" +
            "      \"feeamount\": 0.5\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 10,\n" +
            "      \"feeamount\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 31,\n" +
            "      \"feeamount\": 1.5\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 41,\n" +
            "      \"feeamount\": 3\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 51,\n" +
            "      \"feeamount\": 4.5\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 71,\n" +
            "      \"feeamount\": 7.5\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 101,\n" +
            "      \"feeamount\": 10\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 301,\n" +
            "      \"feeamount\": 15\n" +
            "    },\n" +
            "    {\n" +
            "      \"from\": 501,\n" +
            "      \"feeamount\": 20\n" +
            "    }\n" +
            "  ]\n" +
            "}",JsonObject.class);

    public static BigDecimal getFee(BigDecimal amount, String ccy) throws FileNotFoundException {
        Reader reader = new FileReader("feelist.json");
        JsonObject feeList =gson.fromJson(reader,JsonObject.class).getAsJsonObject();
        BigDecimal fee = new BigDecimal(0);
        if (!feeList.has(ccy)) return fee;
        for (JsonElement e : feeList.get(ccy).getAsJsonArray()){
            FeeItem fi = gson.fromJson(e, FeeItem.class);
            if (fi.from.compareTo(amount) <= 0){
                if (fi.feeamount != null) fee = fi.feeamount;
                else if (fi.feepercent != null) fee = amount.multiply(fi.feepercent).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
            }
        }
        return fee;
    }

    public static List<FeeItem> getFeeList(String ccy) throws FileNotFoundException {
        List<FeeItem> feeItems = new ArrayList<>();
        Reader reader = new FileReader("feelist.json");
        JsonObject feeList =gson.fromJson(reader,JsonObject.class).getAsJsonObject();

        //BigDecimal fee = new BigDecimal(0);
        if (!feeList.has(ccy)) return new ArrayList<FeeItem>();

        for (JsonElement e : feeList.get(ccy).getAsJsonArray()){
            FeeItem fi = gson.fromJson(e, FeeItem.class);
            feeItems.add(fi);
            //System.out.println(feeItems);
        }

        return feeItems;
    }

    /*
    public static void main(String[] args) throws Exception{
        System.out.println(FeeList.getFee(new BigDecimal("5000"), "USD"));
        List<FeeItem> list = FeeList.getFeeList( "LAK");
        System.out.println(list);
    }
    */
}
@Data
class FeeItem{
    BigDecimal from;
    BigDecimal feeamount;
    BigDecimal feepercent;
}
