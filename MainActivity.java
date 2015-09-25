package com.example.ykai.post_name_password;


        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;

        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.protocol.HTTP;
        import org.apache.http.util.EntityUtils;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends ActionBarActivity {

    private Button btnDown;

    private TextView mTextView1;
    private static String uriAPI    = "http://wap.easou.com/";

    private ProgressDialog dialog;
    private   String strResult;
    // 一个静态的Handler，Handler建议声明为静态的
    private static  Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDown = (Button) findViewById(R.id.btnDown);

        mTextView1=(TextView)findViewById(R.id.textView);

        // dialog = new ProgressDialog(this);
        //  dialog.setTitle("提示");
        // dialog.setMessage("正在下载，请稍后...");
        //  dialog.setCancelable(true);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开启一个子线程，用于下载 网页 xml
                new Thread(new MyThread()).start();
                // 显示对话框
                //       dialog.show();
            }
        });
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            // 下载xml
            try {
                //***************************************************************
                //   httpResponse = httpClient.execute(httpGet);
                // if (httpResponse.getStatusLine().getStatusCode() == 200) {
                //   byte[] data = EntityUtils.toByteArray(httpResponse
                //         .getEntity());
                // 得到一个Bitmap对象，并且为了使其在post内部可以访问，必须声明为final
                //final Bitmap bmp=BitmapFactory.decodeByteArray(data, 0, data.length);
              //  uriAPI="http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
                uriAPI="http://192.168.1.101/TPost.aspx";
             uriAPI="http://192.168.1.101:8080/WebRoot/login.do";
uriAPI="http://4dian.sinaapp.com/login";
                HttpPost httpRequest = new HttpPost(uriAPI);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
   //   params.add(new BasicNameValuePair("username", "test1"));params.add(new BasicNameValuePair("password", "test"));
 //  params.add(new BasicNameValuePair("name2", "test"));  params.add(new BasicNameValuePair("website2", "test"));

                params.add(new BasicNameValuePair("email", "841838953@qq.com"));params.add(new BasicNameValuePair("password", "15ad59db"));

                httpRequest.setEntity(new UrlEncodedFormEntity(params,
                        HTTP.UTF_8));
                HttpResponse httpResponse = new DefaultHttpClient()
                        .execute(httpRequest);

                if (httpResponse.getStatusLine().getStatusCode() == 200) {

                    strResult = EntityUtils.toString(httpResponse
                            .getEntity());
                    // mTextView1.setText(strResult);
                } else {
                    // mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString());
                }

                /*catch (ClientProtocolException e) {
                    mTextView1.setText(e.getMessage().toString());
                    e.printStackTrace();
                } catch (IOException e) {
                    mTextView1.setText(e.getMessage().toString());
                    e.printStackTrace();
                } catch (Exception e) {
                    mTextView1.setText(e.getMessage().toString());
                    e.printStackTrace();
                }*/


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 在Post中操作UI组件mTextView
                        btnDown.setText("nihao");
                        mTextView1.setText(strResult);
                    }
                });
                // 隐藏对话框
                //         dialog.dismiss();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
