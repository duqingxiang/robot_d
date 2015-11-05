package com.demo.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


public class DateUtil  {
    
	private static SimpleDateFormat simpleDateFormat; 
	
	static {
		simpleDateFormat = new SimpleDateFormat(); 
	}
	
	public static String getFormatDate(Date date){
		if(date==null){
			return "";
		}
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dateFormat.format(date);
	}
	
	public static boolean isLeapYear(int year){
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) 
			return true;
		return false;
	}
	
	public static Date getNewDateByAdd(Date d, int days) {
		int Year, Month, Day;
		boolean rYear;
		int DayOfMonths[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		Calendar cal;
		cal = Calendar.getInstance();
		cal.setTime(d);
		
		Year = cal.get(Calendar.YEAR);
		Month = cal.get(Calendar.MONTH);
		Day = cal.get(Calendar.DAY_OF_MONTH);
		
		Day = Day + days;
		
		if ((Year % 4 == 0 && Year % 100 != 0) || Year % 400 == 0) {
			rYear = true;
		} else {
			rYear = false;
		}
		
		if (rYear) {
			DayOfMonths[1] = 29;
		} else {
			DayOfMonths[1] = 28;
		}

		while (Day > DayOfMonths[Month]) {
			Day = Day - DayOfMonths[Month];
			Month++;
			if (Month > 11) {
				Month = 0;
				Year++;
			}
		}
		
		cal.set(Year, Month, Day);
		return cal.getTime();
	}
	
	public static Date getDateByString(String dateString) {
		if(dateString == null || dateString.trim().length()==0) 
			return null; 
		Date resultDate = null; 
		try {
			resultDate = simpleDateFormat.parse(dateString.trim()); 
		} catch(java.text.ParseException pe) {
			//do nothing 
		}
		return resultDate; 
	}
	
	
	
	public static Date getSubtractedDate(int numberOfDate) {
        Calendar calendar = Calendar.getInstance();
        int substractedDays = numberOfDate *= -1;
        calendar.add(Calendar.DATE, substractedDays);

        java.util.Date finalDate = calendar.getTime();
		return finalDate;   
	}

	/**
	 * 获 date 时间  elapsedHourValue  小时前的时间
	 * @param elapsedHourValue
	 * @return
	 */
	public static Date getSubtractedDateByElapsedHourValue(Date date ,long elapsedHourValue) {
		long elapsedTimeInMillis = elapsedHourValue * 60 * 60 * 1000; 
		long currentTimeInMillis = date.getTime();

		long previousTimeInMillis = currentTimeInMillis - elapsedTimeInMillis; 
		Date previousDate = new Date(previousTimeInMillis); 
		return previousDate; 
	}
	/**
	 * 获得当前时间  elapsedHourValue  小时前的时间
	 * @param elapsedHourValue
	 * @return
	 */
	public static Date getSubtractedDateByElapsedHourValue(long elapsedHourValue) {
		long elapsedTimeInMillis = elapsedHourValue * 60 * 60 * 1000; 
		long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();

		long previousTimeInMillis = currentTimeInMillis - elapsedTimeInMillis; 
		Date previousDate = new Date(previousTimeInMillis); 
		return previousDate; 
	}
	/**
	 * 获得当前时间  elapsedHourValue  小时前的时间
	 * @param elapsedHourValue
	 * @return
	 */
	public static Date getSubtractedDateByElapsedHourValue(int elapsedHourValue) {
		return getSubtractedDateByElapsedHourValue((long)elapsedHourValue); 
	}

	/**
	 * 获 date 时间  elapsedHourValue  小时前的时间
	 * @param elapsedHourValue
	 * @return
	 */
	public static Date getSubtractedDateByElapsedHourValue(Date date ,int elapsedHourValue) {
		return getSubtractedDateByElapsedHourValue(date ,(long)elapsedHourValue); 
	}
	
	public static Date getSubtractedDateByElapsedHourValue(double elapsedHourValue) {
		long elapsedTimeInMillis = (long)(elapsedHourValue * 60.0 * 60.0 * 1000.0); 
		long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();

		long previousTimeInMillis = currentTimeInMillis - elapsedTimeInMillis; 
		Date previousDate = new Date(previousTimeInMillis); 
		return previousDate; 
	}
	
	public static Date getSubtractedDateByElapsedYearValue(int elapsedYearValue) {
		Calendar calendar = Calendar.getInstance(); 
		int currentYearValue = calendar.get(Calendar.YEAR); 
		calendar.set(Calendar.YEAR, currentYearValue-elapsedYearValue); 
		return new Date(calendar.getTimeInMillis()); 
	}

	public static Calendar getSubtractedCalendarByElapsedYearValue(int elapsedYearValue) {
		Calendar calendar = Calendar.getInstance(); 
		int currentYearValue = calendar.get(Calendar.YEAR); 
		calendar.set(Calendar.YEAR, currentYearValue-elapsedYearValue); 
		return calendar; 
	}
	
	public static Calendar getBeginningOfDayCalendar() {
		Calendar beginningOfDayCalendar = Calendar.getInstance(); 
		beginningOfDayCalendar.set(Calendar.HOUR_OF_DAY, 0);  
		beginningOfDayCalendar.set(Calendar.MINUTE, 0);  
		beginningOfDayCalendar.set(Calendar.SECOND, 0);  
		
		return beginningOfDayCalendar; 
	}

	public static Date getBeginningOfDayDate() {
		Calendar beginningOfDayCalendar = Calendar.getInstance(); 
		beginningOfDayCalendar.set(Calendar.HOUR_OF_DAY, 0);  
		beginningOfDayCalendar.set(Calendar.MINUTE, 0);  
		beginningOfDayCalendar.set(Calendar.SECOND, 0);  
		
		long beginningOfDayInMillis = beginningOfDayCalendar.getTimeInMillis(); 
		Date beginningOfDayDate = new Date(beginningOfDayInMillis); 
		
		return beginningOfDayDate; 
	}

	public static Date getDateFromString(String dateString, String parseString) {
		long longDateValue=0L;
		Date resultDate = null;  
		if(StringUtil.isEmpty(dateString))
			return null; 
		List<String> monthDayYearStringList = StringUtil.parseStringToList(dateString, parseString); 
		int year=0, month=0, day=0;

		try { 
			month = Integer.parseInt((String)monthDayYearStringList.get(1));
//			logger.info("##### DateUtil.getDateFromString(), after parsing, month="+month); 
			if(month > 0)
				month = month-1;			
			day = Integer.parseInt((String)monthDayYearStringList.get(2)); 
			year = Integer.parseInt((String)monthDayYearStringList.get(0)); 
//			logger.info("##### DateUtil.getDateFromString(), after parsing, month="+month+", day="+day+", year="+year); 
			longDateValue = getTimeValueByYearMonthDay(year, month, day); 
			resultDate = new Date(longDateValue); 
		} catch(NumberFormatException nfe) {
			//do nothing
		}
		if(resultDate==null)
			Logs.getinfoLogger().info("#####, within DateUtil.getDateFromString(), right before return, longDateValue is NULL!!!!");
		else 
			Logs.getinfoLogger().info("#####, within DateUtil.getDateFromString(), right before return, longDateValue is NOT NULL!!!!");
	
		return resultDate; 
	}	

    public static long getTimeValueByYearMonthDay(int year,int month,int day){
    	java.util.Calendar calendar = Calendar.getInstance(); 
    	calendar.set(Calendar.YEAR, year); 
    	calendar.set(Calendar.MONTH, month); 
    	calendar.set(Calendar.DAY_OF_MONTH, day); 
    	
    	return calendar.getTimeInMillis();
    }

    public static int getDifferentDaysBetweenTwoDates(Date beforeDate, Date afterDate) {
        // Get difference in milliseconds
        long diffMillis = afterDate.getTime() - beforeDate.getTime();
        
//        // Get difference in seconds
//        long diffSecs = diffMillis/(1000);           
//        
//        // Get difference in minutes
//        long diffMins = diffMillis/(60*1000);        
//        
//        // Get difference in hours
//        long diffHours = diffMillis/(60*60*1000);    
        
        // Get difference in days
        long diffDays = diffMillis/(24*60*60*1000);  
     
        return (int)diffDays; 
    }
    
    public static int getNonceTimeKind(Date startDate,Date endDate,Date nonceDate){
    	long start = startDate.getTime();
    	long end = endDate.getTime();
    	long nonce = nonceDate.getTime();
    	
    	if(nonce < start){
    		return -1;
    	}else if(nonce > end){
    		return 1;
    	}
    	
    	return 0;
    }
	/**
	 * java.sql.Date ==> java.util.Date
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date dateSql2Util(java.sql.Date date) {
		return date;
	}

	/**
	 * String ==> java.util.Date
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static java.util.Date dateString2Util(String dateStr, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(dateStr, new ParsePosition(0));
	}

	/**
	 * java.util.Date ==> String
	 * 
	 * @return
	 */
	public static String dateUtil2String(java.util.Date date, String format) {
		if(date==null) return null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String dateUtil2StringDate(java.util.Date date) {
		if(date==null) return null;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.format(date);
	}

	public static String dateUtil2StringDateTime(java.util.Date date) {
		if(date==null) return null;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return formatter.format(date);
	}
	
	public static Date dateUtil2date(java.util.Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(formatter.format(date), new ParsePosition(0));
	}
	/**
	 * java.util.Date ==> java.sql.Date
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date dateUtil2SQL(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * java.util.Date ==> String ==> java.sql.Date
	 * 
	 * @param date
	 * @return
	 */
	// public java.sql.Date DateUtil2SQL(java.util.Date date, String format) {
	// String dateStr = DateUtil2String(date, format);
	// return DateString2SQL(dateStr, format);
	// }
	/**
	 * String ==> java.sql.Date
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static java.sql.Date dateString2SQL(String dateStr, String format) {
		java.util.Date date = dateString2Util(dateStr, format);
		return dateUtil2SQL(date);
	}

	/**
	 * String ==> java.sql.Timestamp
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static java.sql.Timestamp timestampString2SQL(String dateStr,
			String format) {
		java.util.Date date = dateString2Util(dateStr, format);
		if (null != date) {
			return new Timestamp(date.getTime());
		} else {
			return null;
		}
	}

	/**
	 * java.util.Date date ==> java.sql.Timestamp
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static java.sql.Timestamp timestampDate2SQL(java.util.Date date) {
		if (null != date) {
			return new Timestamp(date.getTime());
		} else {
			return null;
		}
	}

	public static int getYear() {
		return new GregorianCalendar().get(Calendar.YEAR);
	}

	public static int getMonth() {
		return new GregorianCalendar().get(Calendar.MONTH) + 1;
	}
	
	public static String getMonthOfString() {
		int month = new GregorianCalendar().get(Calendar.MONTH) + 1;
		if(month<10){
			return "0"+month;
		}
		return month+"";
	}
	
	public static int getDay() {
		return new GregorianCalendar().get(Calendar.DATE);
	}
	
	public static String getDayOfString() {
		int day = new GregorianCalendar().get(Calendar.DATE);
		if(day<10){
			return "0"+day;
		}
		return day+"";
	}

	public static int getHour() {
		return new GregorianCalendar().get(Calendar.HOUR_OF_DAY);
		//return new GregorianCalendar().get(Calendar.HOUR);
	}

	public static int getMinute() {
		return new GregorianCalendar().get(Calendar.MINUTE);
	}

	public static int getSecond() {
		return new GregorianCalendar().get(Calendar.SECOND);
	}

	public static String getDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date Now = new java.util.Date();
		String NDate = formatter.format(Now);
		return NDate;
	}
	public static String getDatePath(){
		 String t="/";
		 String path=getYear()+t+getMonth()+t+getDay()+t+getHour();
		 return path;
	}
	public static String getImageDatePath(){
		 String path=getYear() + "" + getMonth() + "" + getDay() + "";
		 return path;
	}
	
	public static String getCurrentDate(){
		String t="-";
		String date=getYear()+t+getMonthOfString()+t+getDayOfString();
		return date;
	}
	
	public static long getNumberOfDays(Date date){
		long dateNumber=0l;
		if(date==null){
			return 0l;
		}
		Calendar beginningOfDayCalendar = Calendar.getInstance();
		beginningOfDayCalendar.setTime(new Date(System.currentTimeMillis()));
		beginningOfDayCalendar.set(Calendar.HOUR_OF_DAY, 0);  
		beginningOfDayCalendar.set(Calendar.MINUTE, 0);  
		beginningOfDayCalendar.set(Calendar.SECOND, 0);  
		
		long beginningOfDayInMillis = beginningOfDayCalendar.getTimeInMillis(); 
		beginningOfDayCalendar.setTime(date);
		beginningOfDayCalendar.set(Calendar.HOUR_OF_DAY, 0);  
		beginningOfDayCalendar.set(Calendar.MINUTE, 0);  
		beginningOfDayCalendar.set(Calendar.SECOND, 0);
		long dateMillis = beginningOfDayCalendar.getTimeInMillis(); 
		//Date now=new Date(System.currentTimeMillis());
		//dateNumber=now.getTime()-date.getTime();
		dateNumber=beginningOfDayInMillis-dateMillis;
		dateNumber=dateNumber/(1000*60*60*24);
		if(dateNumber<0){
			dateNumber=0;
		}
		return dateNumber;
	}
	public static String getChinaDate(Date date){
		String s="";
		Long temp;
		long days=getNumberOfDays(date);
		
		if((days/365)>=1l){
			temp=days/365;
			s=temp.toString();
			s= s + "年前";
		}else if((days/31)>=1l){
			temp=days/31;
			s=temp.toString();
			s= s + "月前";
		}else if((days/7)>=1l){
			temp=days/7;
			s = s + "周前";
		}else {
			temp=days;
			if(temp==0){
				s="今天";
			}else{
				s=temp.toString();
				s=s + "天前";
			}
		}
		return s;
	}
	public static Date getDateByString(String dateStr, String format){
		java.text.SimpleDateFormat df=new java.text.SimpleDateFormat(format); 
		Date d1;
		try {
			d1 = df.parse(dateStr);
		} catch (ParseException e) {
			Logs.geterrorLogger().error(e.getMessage(),e);
			return null;
		} 
		return d1;
	}
	
    public static Date[] getThisWeekStartAndEnd(int difDays,Date baseDate){
    	Date[] startEnd=new Date[2];
    	Date startTime,endTime;
    	Calendar start_date=Calendar.getInstance();
    	if(baseDate!=null)
    		start_date.setTime(baseDate);
    	start_date.add(Calendar.DAY_OF_YEAR, difDays);
		start_date.add(Calendar.DAY_OF_WEEK, -1);
	   // logger.info(start_date.getTime().toLocaleString());
		start_date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		start_date.set(Calendar.HOUR_OF_DAY, 0);
		start_date.set(Calendar.MINUTE, 0);
		start_date.set(Calendar.SECOND,0);
		startTime=start_date.getTime();
		start_date.add(Calendar.DAY_OF_MONTH, 6);
		start_date.set(Calendar.HOUR_OF_DAY, 11);
		start_date.set(Calendar.MINUTE, 59);
		start_date.set(Calendar.SECOND, 59);
		endTime=start_date.getTime();
    	startEnd[0]=startTime;
    	startEnd[1]=endTime;
    	return startEnd;
    	
    }
    public static String getPeriodOfTime(){
    	if(DateUtil.getHour()>4&&DateUtil.getHour()<=11){
    		return "早上好";
    	}else if(DateUtil.getHour()>11&&DateUtil.getHour()<=13){
    		return "中午好";
    	}else if(DateUtil.getHour()>13&&DateUtil.getHour()<=18){
    		return "下午好";
    	}else{
    		return "晚上好";
    	}
    }
    public static Date getDateBetweenTwoDate(Date startDate,Date endDate){
    	Calendar beginningOfDayCalendar = Calendar.getInstance();
    	Calendar endningOfDayCalendar = Calendar.getInstance(); 
		beginningOfDayCalendar.setTime(startDate);	
		endningOfDayCalendar.setTime(endDate);
		long beginM = beginningOfDayCalendar.getTimeInMillis();
		long endM = endningOfDayCalendar.getTimeInMillis(); 
		long m=beginM;
		long difM=endM-beginM;
		if(difM<=0){
			return new Date(beginM);
		}
		
		Random r=new Random();
		long rM=r.nextLong();
		if(rM<0){
			rM=-1l*rM;
		}
		rM=rM%difM;
		m+=rM;
		if(m<beginM){
			m=beginM;
		}
		if(m>endM){
			m=endM;
		}
		Date date=new Date(m);
		
		return date;
    }
   
    
    public static Date getDate(){
		Calendar beginningOfDayCalendar = Calendar.getInstance(); 
		beginningOfDayCalendar.set(Calendar.HOUR_OF_DAY, 0);  
		beginningOfDayCalendar.set(Calendar.MINUTE, 0);  
		beginningOfDayCalendar.set(Calendar.SECOND, 0);  
		beginningOfDayCalendar.set(Calendar.MILLISECOND, 0); 
		long beginningOfDayInMillis = beginningOfDayCalendar.getTimeInMillis(); 
		Date beforeDate = new Date(beginningOfDayInMillis);
		return beforeDate;
	}
	public static String getDateStr(){
		 GregorianCalendar   g=new   GregorianCalendar(); 
		 String year= String.valueOf(g.get(GregorianCalendar.YEAR));
		 String month= String.valueOf(g.get(GregorianCalendar.MONTH)+1);
		 String day= String.valueOf(g.get(GregorianCalendar.DAY_OF_MONTH));
		 String hour=String.valueOf(g.get(GregorianCalendar.HOUR_OF_DAY));
		 String minute=String.valueOf(g.get(GregorianCalendar.MINUTE));
		 String second=String.valueOf(g.get(GregorianCalendar.SECOND));
		 
		 if(month.length()<2){
			 month="0"+month;
		 }
		 if(day.length()<2){
			 day="0"+day;
		 }
		 if(hour.length()<2){
			 hour="0"+hour;
		 }
		 if(minute.length()<2){
			 minute="0"+minute;
		 }
		 if(second.length()<2){
			 second="0"+second;
		 }
		 //System.out.println(year+month+day+"_"+hour+minute+second);
		 return year+month+day+"_"+hour+minute+second;
	}
    
    /**
     * 获得据今天dateNum间隔的时间
     * @param date			基准日期
     * @param dateNum		时间间隔。
     * @param type			dateNum代表的类型。如Calendar.SECOND代表秒, Calendar.DATE代表天
     * @return				异常情况返回NULL
     */
    public static Date getDate(Date date, int dateNum, int type){
    	try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(type, dateNum);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
    }
    public static boolean getDateDispatchToHour(Date start,Date end){
    	long createDate = start.getTime();
		long currentDate = end.getTime();
		long result = currentDate - createDate;
		int date = (int)(result/(60*1000));
		boolean falg=false;
		if(date<=0){
			falg=true;
		}
		if(date>=60){
			date = (int)(date/60);
			 
			if(date>=24){
				date = (int)(date/24);
				if(date<=2){
					falg=true;
				}
			}else{
				falg=true;
			}
		} else{
			falg=true;
		}
    	return falg;
    }
    
    
    public static String getDateDispatch(Date start,Date end){
    	long createDate = start.getTime();
		long currentDate = end.getTime();
		long result = currentDate - createDate;
		int date = (int)(result/(60*1000));
		String str = date+"<span>分钟前</span>";
		if(date<=0){
			str = "<span>1分钟前</span>";
		}
		if(date>=60){
			date = (int)(date/60);
			str = date+"<span>小时前</span>";
			if(date>=24){
				date = (int)(date/24);
				str = date+"<span>天前</span>";
				if(date>=30){
					date = (int)(date/30);
					str = date+"<span>个月前</span>";
				}
			}
		} 
    	return str;
    }
    public static String getDateDispatchChange(Date start,Date end,int type){
    	long createDate = start.getTime();
		long currentDate = end.getTime();
		long result = currentDate - createDate;
		int date = (int)(result/(60*1000));
		String str;
		if(type == 1){
			 str = date+"<span> minutes before</span>";
		}else{
			 str = date+"<span>分钟前</span>";
		}

		if(date<=0){
			if(type == 1){
				str = "<span>1分钟前</span>";
			}else{
				str = "<span>1 minutes before</span>";
			}
		}
		if(date>=60){
			if(type == 1){
				date = (int)(date/60);
				str = date+"<span> hours ago</span>";
				if(date>=24){
					date = (int)(date/24);
					str = date+"<span> Days before</span>";
					if(date>=30){
						date = (int)(date/30);
						str = date+"<span> months before</span>";
					}
				}
			}else{
				date = (int)(date/60);
				str = date+"<span>小时前</span>";
				if(date>=24){
					date = (int)(date/24);
					str = date+"<span>天前</span>";
					if(date>=30){
						date = (int)(date/30);
						str = date+"<span>个月前</span>";
					}
				}
			}
		}
    	return str;
    }

    public static String getDateDispatchByIndexPage(Date start,Date end){
    	long createDate = start.getTime();
		long currentDate = end.getTime();
		long result = currentDate - createDate;
		int date = (int)(result/(60*1000));
		String str = date+"<span>分钟前</span>登录过";
		if(date<=0){
			str = "<span>1分钟前</span>登录过";
		}
		if(date>=60){
			date = (int)(date/60);
			str = date+"<span>小时前</span>登录过";
			if(date>=24){
				date = (int)(date/24);
				str = date+"<span>天前</span>登录过";
				if(date>5){
					str = "";
				}
			}
		} 
    	return str;
    }
    public static String getDateStr(Date start,Date end){
    	long createDate = start.getTime();
		long currentDate = end.getTime();
		long result = currentDate - createDate;
		int date = (int)(result/(60*1000));
		String str = date+"分钟前";
		if(date<=0){
			str = "1分钟前";
		}
		if(date>=60){
			date = (int)(date/60);
			str = date+"小时前";
			if(date>=24){
				date = (int)(date/24);
				str = date+"天前";
				if(date >= 7){
					date = (int)(date/7);
					str = date+"周前";
					if(date >= 4){
						str = dateUtil2String(start, "yyyy-MM-dd");
					}
				}
			}
		} 
    	return str;
    }
    
    
    public static long getDateDispatchNowToCurrentDayEnd() {
      	Calendar cal =  Calendar.getInstance();
	    Date date = cal.getTime();
	    try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
		} catch (ParseException e) {
			return 24*60*60*1000L;
		}
	    cal.setTime(date);
	    cal.add(Calendar.DAY_OF_MONTH,1);
	    cal.add(Calendar.SECOND,-1);
	    date = cal.getTime();
	    
	    Date cur_date = new Date(System.currentTimeMillis()); 
	    return date.getTime() - cur_date.getTime();
    }
    public static String getTeamTaoLunDate(Date date){
    	long time = (System.currentTimeMillis() - date.getTime());
    	if(time < 60*1000l){
    		return time/1000 +"秒前";
    	}else if(time < 60*60*1000l){
    		return time/(1000*60)+"分钟前";
    	}else if(time < 24*60*60*1000l){
    		return dateUtil2String(date, "HH:mm");
    	}else if(time < 30*24*60*60*1000l){
    		return dateUtil2String(date, "MM-dd");
    	}else {
    		return dateUtil2String(date, "yyyy-MM");
    	}
    	
    }
    
    /**
	 * 获取今天日期以前，或以后的时间
	 * 如果是正数则是以后的时间，如果是负数则是以前的时间
	 */
    public static Date getDateInHourAgo(Date date,int hourNum){
         Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(Calendar.HOUR, hourNum);
		 return cal.getTime();
	}
    
    /**
	 * 获取当前日期是星期几
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0)
            w = 7;

        return w;
    }
    
    /**
     * date 日期加上，或减去几天
     * @param date
     * @param dateNum
     * @return
     */
    public static Date getDateInDayAgo(int dateNum){
		 Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.DATE, dateNum);
		 return cal.getTime();
	}
    
    
    /**
     * 连个日期相差的小时数
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getHour(Date startDate,Date endDate){
    	long totalDate = 0;   
 	    Calendar calendar = Calendar.getInstance();   
 	    calendar.setTime(startDate);   
 	    long timestart = calendar.getTimeInMillis();   
 	    calendar.setTime(endDate);   
 	    long timeend = calendar.getTimeInMillis();   
 	    totalDate = Math.abs((timeend - timestart))/(1000*60*60);   
 	    return totalDate;
    }
    /**
     * 连个日期相差的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDays(Date startDate,Date endDate){
    	long totalDate = 0;   
 	    Calendar calendar = Calendar.getInstance();   
 	    calendar.setTime(startDate);   
 	    long timestart = calendar.getTimeInMillis();   
 	    calendar.setTime(endDate);   
 	    long timeend = calendar.getTimeInMillis();   
 	    totalDate = Math.abs((timeend - timestart))/(1000*60*60*24);   
 	    return totalDate;
    }
    /**
     * 传进日期是当年的第几周
     * @param date
     * @return
     */
    public static int getWeek(Date date){
    	Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * date 日期加上，或减去几天
     * @param date
     * @param dateNum
     * @return
     */
    public static Date getDateInDayAgo(Date date,int dateNum){
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(Calendar.DATE, dateNum);
		 return cal.getTime();
	}
    /**
     * 获取 date 日期加上  或  减去几月
     * @param date
     * @param monthNum
     * @return
     */
    public static Date getDateInMonthAgo(Date date,int monthNum){
    	Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(Calendar.MONTH, monthNum);
		 return cal.getTime();
    }
    
    public static Date getDateInMonthAgo(int monthNum){
		 return getDateInMonthAgo(new Date(), monthNum);
    }
    /**
     * date 分钟
     * @param date
     * @param dateNum
     * @return
     */
    public static Date getDateInMinuteAgo(Date date,int minute){
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(Calendar.MINUTE, minute);
		 return cal.getTime();
	}
    
    private static long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
    	  Date date = null;   //定义时间类型       
    	  SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  try { 
    	  date = inputFormat.parse(inVal); //将字符型转换成日期型
    	  } catch (Exception e) { 
    		  Logs.geterrorLogger().error(e.getMessage(),e);   
    	  } 
    	  return date.getTime();   //返回毫秒数
    } 


    
    public static long getMillisecond(String val1,String val2){
    	 long startT=fromDateStringToLong(val1); //定义上机时间
    	 long endT=fromDateStringToLong(val2);  //定义下机时间

    	 return startT-endT;
    }
    
    //根据给定日期，返回该日期所在星期内的开始时间与结束时间
    public static String[] getMonAndSun(Date date){
    	String[] monAndSun={"",""};
    	if(date==null)return monAndSun;
    	
    	int d=DateUtil.getWeekOfDate(date);
    	Date mon=DateUtil.getDateInDayAgo(date, 1-d);
    	Date sun=DateUtil.getDateInDayAgo(date, 7-d);
    	monAndSun[0]=DateUtil.dateUtil2String(mon, "yyyy-MM-dd")+" 00:00:00";
    	monAndSun[1]=DateUtil.dateUtil2String(sun, "yyyy-MM-dd")+" 59:59:59";
    	return monAndSun;
    }
    
    //返回给定时间的下下周一
    public static Date getLastDate(Date now){
		if(now == null) now = new Date();
		Date lastDate = null;
		int d = DateUtil.getWeekOfDate(now);
		d = 15 - d;
		lastDate = DateUtil.getDateInDayAgo(now, d);
		return lastDate;
	}
    
    public static Date getNexWeek(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		Date date = cal.getTime();
		return date;
    }
    
    /**
     * 获得下一个星期五
     * @param date
     * @return
     */
    public static Date getNexFriday(Date date){
    	date = getDateInDayAgo(date,14);
    	int week = getWeekOfDate(date);
    	if(week == 5){
    		return date;
    	}
    	Date d = null;
    	int i = week-5;
    	if(i>0){
    		d = getDateInDayAgo(date,-i);
    	}else{
    		i = 7-(5-week);
    		d = getDateInDayAgo(date,-i);
    	}
    	return d;
    }
    
    /**
     * 获得当前日期下个月的最后一个星期五日期
     * 
     * @param date
     * @return
     */
    public static Date getLastFriday(Date date) {
    	date.setMonth(date.getMonth()+2);
    	date.setDate(1);
    	//获得这个月最后一天 判断星期
    	Date d = getDateInDayAgo(date,-1);
    	int week = getWeekOfDate(d);
    	if(week == 5){
    		return d;
    	}
    	int i = week-5;
    	if(i>0){
    		d = getDateInDayAgo(d,-i);
    	}else{
    		i = 7-(5-week);
    		d = getDateInDayAgo(d,-i);
    	}
    	return d;
    } 
    
    public static void main(String[] args) throws ParseException {
    	
    	System.out.println(DateUtil.dateUtil2String(getNexFriday(new Date()),"yyyy-MM-dd"));
//    	Date[] dates = getThisWeekStartAndEnd(1, new Date());
//    	System.out.println(dates[0]);
//    	System.out.println(dates[1]);
//    	System.out.println(getMillisecond("2005-03-10 14:52:23", "2005-03-03 14:52:23"));
//    	System.out.println(getDateInMonthAgo(new Date(), 1).toLocaleString());
//    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd "+"14:52:23");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd "+"15:52:23");
//		String d1 = sdf1.format(DateUtil.getDateInDayAgo(-1));
//		String d2 = sdf2.format(new Date());
//    	System.out.println(d1 +"===="+d2);
//    	Date d = DateUtil.getDateByString(d1);
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	
//    	System.out.println(sdf.parse(d1)+"======"+sdf.parse(d2));
    	//		Date d1 = DateUtil.dateString2Util(now + " 19:00:00", "yyy-MM-dd HH:mm:ss");
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd 22:00:00");
//		String begin = sdf.format(d2);
//		logger.error(d2.toString() +"\t" + d1.toString()+"--------------------");
    }
    
    /**
     * 判断 此时间是否在当前时间之后( 当前时间小于此时刻)
     * @param time 格式" 10:50:00"
     * @return time>当前时刻 是 true 否 false
     */
    public static boolean isAfterNowDate(String time){
    	String curDateStr = DateUtil.dateUtil2String(new Date(), "yyyy-MM-dd");
    	Date curDate = DateUtil.dateString2Util(curDateStr+time, "yyyy-MM-dd HH:mm:ss");
    	if(curDate == null){
    		return false;
    	}
    	if(curDate.after(new Date())){
			return true;
		}
    	return false;
    }
    /**
     * 比较两个时刻
     * @param time 格式"2012-11-11 11:11:00"
     * @return timeA>timeB是 true 否 false
     */
    public static boolean compare(String timeA,String timeB){
    	Date dateA = DateUtil.dateString2Util(timeA, "yyyy-MM-dd HH:mm:ss");
    	Date dateB = DateUtil.dateString2Util(timeB, "yyyy-MM-dd HH:mm:ss");
    	if(dateA == null || dateB == null){
    		return false;
    	}
    	if(dateA.after(dateB)){
			return true;
		}
    	return false;
    }
    
    /**
   	 * 获得当前时间elapsedSecondValue前后的时间 
   	 * @param minutes 毫秒
   	 * @return
   	 */
       public static Date getSubtractedDateByElapsedMinutesValue(double minutes) {
   		long elapsedTimeInMillis = (long)(minutes); 
   		long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();

   		long previousTimeInMillis = currentTimeInMillis - elapsedTimeInMillis; 
   		Date previousDate = new Date(previousTimeInMillis); 
   		return previousDate; 
   	}
    
    
    
    /**
   	 * 获得当前时间elapsedSecondValue前后的时间 
   	 * @param elapsedSecondValue
   	 * @return
   	 */
       public static Date getSubtractedDateByElapsedSecondValue(double elapsedSecondValue) {
   		long elapsedTimeInMillis = (long)(elapsedSecondValue  * 60.0 * 1000.0); 
   		long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();

   		long previousTimeInMillis = currentTimeInMillis - elapsedTimeInMillis; 
   		Date previousDate = new Date(previousTimeInMillis); 
   		return previousDate; 
   	}

	/**
	 * 获得当前时间elapsedSecondValue前后的时间
	 * 
	 * @param elapsedSecondValue
	 *            分钟
	 * @return
	 */
	public static Date getSubtractedDateByElapsedSecondValue(
			long currentTimeInMillis, double elapsedSecondValue) {
		long elapsedTimeInMillis = (long) (elapsedSecondValue * 60.0 * 1000.0);

		long previousTimeInMillis = currentTimeInMillis - elapsedTimeInMillis;
		Date previousDate = new Date(previousTimeInMillis);
		return previousDate;
	}

	/**
	 * 获取一个月之前的日期
	 * @return
	 */
	public static String lastMonth() {
		Date date = new Date();

		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1;
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
		if (month == 0) {
			year -= 1;
			month = 12;
		} else if (day > 28) {
			if (month == 2) {
				if (year % 0 == 0 || (year % 4 == 0 && year != 0)) {
					day = 29;
				} else
					day = 28;
			} else if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day == 31) {
				day = 30;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";

		return y + "-" + m + "-" + d;
	}
	/**
	 * 获得本周几的日期 周日是一周的第一天
	 * @param week
	 * @return mm-dd
	 */
	public static String getWeekDate(int week) {
		int nowWeek = DateUtil.getWeekOfDate(new Date());
		int differDay = nowWeek==7?(week==7?0:week):(week-nowWeek);
		String weekDate = DateUtil.dateUtil2String(DateUtil.getNewDateByAdd(new Date(), differDay), "MM-dd");
		return weekDate;
	}
	/**
	 * 获得指定日期的后一天
	 * @param dateStr yyyy-MM-dd
	 * @return
	 */
	public static String getAfterDay(String dateStr) {
		
		String afterDay = "";
		Date originDate = DateUtil.dateString2Util(dateStr, "yyyy-MM-dd");
		afterDay = DateUtil.dateUtil2String(DateUtil.getNewDateByAdd(originDate, 1), "yyyy-MM-dd");
		return afterDay;
	}
	
	/**
	 * 获得指定日期的前一天
	 * @param dateStr
	 * @return
	 */
	public static String getPreDay(String dateStr) {
		
		String afterDay = "";
		Date originDate = DateUtil.dateString2Util(dateStr, "yyyy-MM-dd");
		afterDay = DateUtil.dateUtil2String(DateUtil.getNewDateByAdd(originDate, -1), "yyyy-MM-dd");
		return afterDay;
	}
	
	/**
	 * 获得本周几的日期 周一是一周的第一天
	 * @param week
	 * @return mm-dd
	 */
	public static String getWeekDateFirstMonday(int week) {
		int nowWeek = DateUtil.getWeekOfDate(new Date());
		int differDay = nowWeek==1?(week==1?0:week):(week-nowWeek);
		String weekDate = DateUtil.dateUtil2String(DateUtil.getNewDateByAdd(new Date(), differDay), "yyyy-MM-dd");
		return weekDate;
	}
	
	/**
	 * 根据秒数获得数据类型
	 * @param second
	 * @return
	 */
	public static int returnDataType(int second){
		int result = 0;
		switch (second) {
		case 60:
			result=0;
			break;
		case 180:
			result=7;
			break;
		case 300:
			result=1;
			break;
		case 900:
			result=2;
			break;
		case 1800:
			result=9;
			break;
		case 3600:
			result=10;
			break;
		case 7200:
			result=11;
			break;
		case 14400:
			result=12;
			break;
		case 21600:
			result=13;
			break;
		case 43200:
			result=14;
			break;
		case 86400:
			result=3;
			break;
		case 259200:
			result=15;
			break;
		case 604800:
			result=4;
			break;
		default:
			result=2;
			break;
		}
		//  3:日 4:周 5:月 6:年    14:12小时 15：3日',
		return result;
	}
	
	public static String getWeekStr(Date day){
		int week = getWeekOfDate(day);
		switch (week) {
			case 1:return "星期一";
			case 2:return "星期二";
			case 3:return "星期三";
			case 4:return "星期四";
			case 5:return "星期五";
			case 6:return "星期六";
			case 7:return "星期日";
		}
		return "";
	}
	/**
	 * 返回长度为6最近的16点、8点、0点的日期数组
	 * @return
	 */
	public static Date[] getDateInDayZero(){
    	Date[] arrDate = new Date[6];
    	Date date = new Date();
    	date.setMinutes(0);
    	date.setSeconds(0);
    	if(date.getHours()<8){
    		date.setHours(0);
    	}else if(date.getHours()<16){
    		date.setHours(8);
    	}else{
    		date.setHours(16);
    	}
    	for(int i=0;i<6;i++){
    		arrDate[i] = DateUtil.getDateInHourAgo(date, -8*i);
    	}
    	return arrDate;
    }
    /**
     * 通过通过间隔时间获取当前时间之前的时间点
     * @param date 时间
     * @param interval 间隔
     * @return
     */
    public  long compute_date(long date,long interval){

        long ys = date%interval;

        long result = date - ys;

        return result;
    }
    
    public static String getDateStringByTimestamp(long ts){
		Timestamp timestamp = new Timestamp(ts);
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(timestamp);	
	}
}
