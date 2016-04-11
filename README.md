# Android RESTFull Enabled Base Project
A Basic Android App with a Volley Implementation and POJO support.

If you need a generic RESTFull Web Service classes to manage the service responses on your project you can use this sample as a base.
You will need this classes:
* **BaseApplication:** Singleton Application class to manage Volley Queue
* **ObjectRequest:** Custom Request class, manages the endpoints and the POJO castings
* **ServiceHandler:** Endpoint manager, use this class to encapsulate the Volley Request and Responses
* **LruBitmapCache:** Internal class to manage Volley Image Cache  

## Use
You only need to setup the project and then start using the classes. For example if you want to call a endpoint using post you need to use this code on your activity:

```java
ServiceHandler handler = new ServiceHandler();
Map<String, String> params = new ArrayMap<String, String>();
params.put("title", "foo");
params.put("body", "bar");
params.put("userId", "1");
handler.addHeader("Content-Type","Application/json");

handler.objectRequest("http://yourrestapi.com/posts", Request.Method.POST,
                params, JSONObject.class, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", error.getMessage());
                    }
                });

```

If you need call a endpoint using a get and also send params, you only have to change the Method. The object request will encode the get methods on the url.

```java
ServiceHandler handler = new ServiceHandler();
Map<String, String> params = new ArrayMap<String, String>();
params.put("appId", "32jdhsbhd72");
handler.objectRequest("http://yourgetapi.com", Request.Method.GET,
                params, JSONObject.class, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", error.getMessage());
                    }
                });
```

If you want to serialize the service response on a POJO, pass the class to the ServiceHandler.

First create a object and make sure your json field names are the same as your object properties.

```java
public class User {

    private int id;
    private String name;
    private String last_name;
    private String email;

    public Weather() {

    }
}
``` 

The JSON response should be:

```json
{"user": {"id":1, "name":"John", "last_name":"White", "email":"jwhite@support.com"}}
```

Pass the User class to the Service Handler

```java
ServiceHandler handler = new ServiceHandler();
Map<String, String> params = new ArrayMap<String, String>();
params.put("appId", "32jdhsbhd72");
handler.objectRequest("http://youruserapi.com", Request.Method.GET,
                params, User.class, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(User response) {
                        Log.d("Response", response.getName());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", error.getMessage());
                    }
                });
```

## Contributing
1. Fork it!
2. Create your feature branch: git checkout -b my-new-feature
3. Commit your changes: git commit -am 'Add some feature'
4. Push to the branch: git push origin my-new-feature
5. Submit a pull request

## Credits

David Cortes [@dcortes22](http://www.twitter.com/dcortes22)

[https://medium.com/@dcortes22](https://medium.com/@dcortes22)
