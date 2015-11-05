package com.demo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Logs {

		private final static  Log infolog;
		private final static  Log slowlog;
		private final static  Log errorlog;
		
		static{
			infolog= LogFactory.getLog(Logs.class);
			slowlog=LogFactory.getLog("slowLog");
			errorlog=LogFactory.getLog("errorLog");
		}

		public static Log getinfoLogger(){
		    return infolog;
		}
		
		public static Log getslowLogger(){
		    return slowlog;
		}
		public static Log geterrorLogger(){
		    return errorlog;
		}

        public static boolean ifLog(int n) {
            return System.currentTimeMillis() % n == 0;
        }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logs.geterrorLogger().error("123");
	}

}
