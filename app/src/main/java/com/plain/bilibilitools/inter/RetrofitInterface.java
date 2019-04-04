package com.plain.bilibilitools.inter;

import com.plain.bilibilitools.bean.ResultBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Create by Plain on 2019/4/4 10:07 PM
 * Description:
 */
public interface RetrofitInterface {

    @GET("x/relation/stat?")
    Call<ResultBean> getCall(@Query("vmid") String avNumber);

}
