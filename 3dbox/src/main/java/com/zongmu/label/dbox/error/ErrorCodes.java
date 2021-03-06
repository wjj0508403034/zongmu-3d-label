package com.zongmu.label.dbox.error;

public class ErrorCodes {
	public static final String Send_Mail_Failed = "E00000";
	public static final String Parse_Email_Template_Failed = "E00001";

	public final static String Login_Failed_Due_To_User_Not_Exists = "E10001";
	public final static String Login_Failed_Due_To_User_Not_Active = "E10002";
	public final static String Login_Failed_Due_To_User_Locked = "E10003";
	public final static String Login_Failed_Due_To_Password_Failed_Many_Times = "E10004";
	public final static String Login_Failed_Due_To_Password_Invalid = "E10005";
	
	public final static String Register_Failed_Due_To_Email_Is_Empty = "E10006";
	public final static String Register_Failed_Due_To_Password_Is_Empty = "E10007";
	public final static String Register_Failed_Due_To_Password_Not_Match = "E10008";
	public final static String Register_Failed_Due_To_User_Exists = "E10009";

	public final static String Video_Attr_Exists = "E20001";
	public final static String Video_Attr_Not_Exists = "E20002";
	public final static String Video_Attr_Value_Exists = "E20003";
	public final static String Video_Attr_Value_Not_Exists = "E20004";
	public final static String Reason_Exists = "E20005";
	public static final String Reason_Not_Exists = "E20006";
	
	public static final String General_Error = "E90000";
	public static final String BAD_REQUEST_NOT_ACCEPT_CONTENT_TYPE = "E90001";
}
