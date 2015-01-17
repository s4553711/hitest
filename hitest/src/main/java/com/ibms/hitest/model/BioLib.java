package com.ibms.hitest.model;

import java.io.File;

public class BioLib {

	private String path;
	private String refName;

	public boolean folderExist() {
		return new File(path).exists();
	}

	public boolean typeCheck() {
		return new File(path+"/"+refName+".fa").exists() && new File(path+"/"+refName+".fa.fai").exists() && 
				new File(path+"/contig.flank.intervals").exists() && new File(path+"/contig.flank.sort.intervals").exists();
	}
	
	public boolean check() {
		boolean result = true;
		result &= folderExist();
		result &= typeCheck();
		return result;
	}

	public void setPath(String str) {
		this.path = str;
	}

	public void setRefName(String str) {
		refName = str;
		
	}
}
