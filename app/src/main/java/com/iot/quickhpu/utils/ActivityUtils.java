package com.iot.quickhpu.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

public class ActivityUtils {

	// 跳转到另一个Activity
	public static void toAnotherActivity(Activity context,Class<?> target){
		Intent intent=new Intent(context,target);
		context.startActivity(intent);
	}

	// 携带数据跳转到另一个Activity
	public static void toAnotherActivityWithData(Activity context, Class<?> target, String key,Serializable data){
		Intent intent=new Intent(context,target);
		intent.putExtra(key,data);
		context.startActivity(intent);
	}
	
}
