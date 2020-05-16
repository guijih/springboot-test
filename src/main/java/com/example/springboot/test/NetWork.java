package com.example.springboot.test;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NetWork {

	 private static final String ENCODING = "UTF-8";
	 
	 private static final Logger log = LoggerFactory.getLogger(NetWork.class);
	 
	    public static String post(String url, Map<String, String> paramsMap) {
	        CloseableHttpClient client = HttpClients.createDefault();
	        String responseText = "";
	        CloseableHttpResponse response = null;
	        try {
	            HttpPost method = new HttpPost(url);
	            if (paramsMap != null) {
	                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
	                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
	                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
	                    paramList.add(pair);
	                }
	                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
	            }
	            response = client.execute(method);
	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	                responseText = EntityUtils.toString(entity);
	            }
	        } catch (Exception e) {
	            log.error("http request failed",e);
	        } finally {
	            try {
	                response.close();
	            } catch (Exception e) {
	                log.error("",e);
	            }
	        }
	        return responseText;
	    }
	 
	    public static String get(String url, Map<String, String> paramsMap) {
	        CloseableHttpClient client = HttpClients.createDefault();
	        String responseText = "";
	        CloseableHttpResponse response = null;
	        try {
	            String getUrl = url+"?";
	            if (paramsMap != null) {
	                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
	                    getUrl += param.getKey() + "=" + URLEncoder.encode(param.getValue(), ENCODING)+"&";
	                }
	            }
	            HttpGet method = new HttpGet(getUrl);
	            response = client.execute(method);
	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	                responseText = EntityUtils.toString(entity);
	            }
	        } catch (Exception e) {
	            log.error("http request failed",e);
	        } finally {
	            try {
	                response.close();
	            } catch (Exception e) {
	                log.error("",e);
	            }
	        }
	        return responseText;
	    }
	        //post请求参数为json格式
	    public static String HttpPostWithJson(String url, String json) {
	        String returnValue = "这是默认返回值，接口调用失败";
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        try{
	            //第一步：创建HttpClient对象
	            httpClient = HttpClients.createDefault();
	 
	            //第二步：创建httpPost对象
	            HttpPost httpPost = new HttpPost(url);
	 
	            //第三步：给httpPost设置JSON格式的参数
	            StringEntity requestEntity = new StringEntity(json,"utf-8");
	            requestEntity.setContentEncoding("UTF-8");
	            httpPost.setHeader("Content-type", "application/json");
	            httpPost.setEntity(requestEntity);
	 
	            //第四步：发送HttpPost请求，获取返回值 //调接口获取返回值时，必须用此方法
	            returnValue = httpClient.execute(httpPost,responseHandler);
	 
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	 
	        finally {
	            try {
	                httpClient.close();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        //第五步：处理返回值
	        return returnValue;
	    }

	    static class Param{

	    	private Body body;
			private Head head;

			public Body getBody() {
				return body;
			}

			public void setBody(Body body) {
				this.body = body;
			}

			public Head getHead() {
				return head;
			}

			public void setHead(Head head) {
				this.head = head;
			}

			static class Body{
				private String page;
				private String proComCode;
				private String searchBrand;
				private String size;

				public String getPage() {
					return page;
				}

				public void setPage(String page) {
					this.page = page;
				}

				public String getProComCode() {
					return proComCode;
				}

				public void setProComCode(String proComCode) {
					this.proComCode = proComCode;
				}

				public String getSearchBrand() {
					return searchBrand;
				}

				public void setSearchBrand(String searchBrand) {
					this.searchBrand = searchBrand;
				}

				public String getSize() {
					return size;
				}

				public void setSize(String size) {
					this.size = size;
				}
			}

			static class Head{
				private String access_token;

				public String getAccess_token() {
					return access_token;
				}

				public void setAccess_token(String access_token) {
					this.access_token = access_token;
				}
			}
		}

	 
	public static void main(String[] args) {

		Param.Body body=new Param.Body();
		body.setPage("1");
		body.setProComCode("11000000");
		body.setSearchBrand("宝马");
		body.setSize("10");

		Param.Head head=new Param.Head();
		head.setAccess_token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImV4cCI6MTU4OTAzMjI0NywidXNlcm5hbWUiOiJ5ZGNrIn0.jGhC8nIwqeGT_pyOtUWFJsdW9wGCpUAKLn--dCgI59U");

		Param param=new Param();
		param.setBody(body);

		param.setHead(head);


		String json="{\"body\":{\"page\":\"1\",\"proComCode\":\"11000000\",\"searchBrand\":\"宝马\",\"size\":\"10\"},\"head\":{\"access_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImV4cCI6MTU4OTAzMjI0NywidXNlcm5hbWUiOiJ5ZGNrIn0.jGhC8nIwqeGT_pyOtUWFJsdW9wGCpUAKLn--dCgI59U\"}}";
		String json1=JSON.toJSONString(param);

		String url="http://106.14.132.120:9081/api/app/claimMobile/searchStdBrand";


		String result=HttpPostWithJson(url,json1);
		System.out.println(result);
	}
	
}
