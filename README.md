# RetrofitHelper

[![](https://img.shields.io/badge/release-1.0.0-blue.svg)](https://github.com/yfbx/retrofit-helper/releases) 

#### Add the dependency
```
repositories {
	maven { url 'https://jitpack.io' }
}
```

```
dependencies {
	implementation 'com.github.yfbx:retrofit-helper:1.0.0'
}
```

#### How to use
- Build Retrofit
```

 val baseUrl = "https://www.yuxiaor.com/api/v1/"

 fun getRetrofit(): Retrofit {
    return ReBuilder(baseUrl)
        .addHeader("", "")
        .addGsonConverter()
        .debug(BuildConfig.DEBUG)
        .build()
 }
    
    
 inline fun <reified T : Any> create(): T {
   return getRetrofit().create(T::class.java)
 }
```

- Sample

```
   Net.create<UserApi>().login(user, password).enqueue(NetCallback {
   
       // TODO

  })
```

- Callbacks    
  This Project includes callbacks below:    
      -NetCallback   
      -FileCallbac    
      -BitmapCallback    
  You can also extend your custom callbacks    

