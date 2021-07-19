package com.ccnu.learningService.controller;

import com.ccnu.learningService.config.SwaggerConfig;
import com.ccnu.learningService.service.impl.TextServiceImpl;
import com.ccnu.learningService.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 短文本 前端控制器
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Api(tags = { SwaggerConfig.TAG_8 })
@RestController
@RequestMapping("/learningService/text")
@CrossOrigin
public class TextController {

    @ApiOperation(value = "短文本情感分析")
    @PostMapping("emotionAnalysis")
    public Result emotionAnalysis(@RequestBody String content) {
        Double result = emotionalOrientation(content);
        return Result.ok().data("result", result);
    }

    public Double emotionalOrientation(String content) {
        String access_token = getAuth();
        String authHost = "https://aip.baidubce.com/rpc/2.0/nlp/v1/sentiment_classify?";
        String getPositiveProb = authHost
                + "&access_token=" + access_token;
        try {
            URL url = new URL(getPositiveProb);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");//请求post方式
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //header内的的参数在这里set    connection.setRequestProperty("健, "值");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.connect();
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"GBK");

            //body参数在这里put到JSONObject中

            JSONObject parm = new JSONObject();
            parm.put("text", content);

            writer.write(parm.toString());
            writer.flush();



            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            connection.disconnect();

            System.out.println(result);

            JSONObject jsonObject = new JSONObject(result.toString());
            JSONArray items = jsonObject.getJSONArray("items");
            JSONObject item = items.getJSONObject(0);
            return item.getDouble("positive_prob");
        } catch (Exception e) {
            System.err.print("获取positive_prob失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "nGNYykPPxSusztzs01iLyr33";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "dBH82MhyYdqvINX7WG4lQdzPFOea7ZWF";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result.toString());
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            System.err.print("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }
}

