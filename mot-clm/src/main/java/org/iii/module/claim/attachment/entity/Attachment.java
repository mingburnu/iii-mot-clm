package org.iii.module.claim.attachment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 附件管理系統
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/5/20
 */
@Entity
@Table(name = "CLM_ATTACHMENT")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Attachment extends GenericEntity {

	private static final long serialVersionUID = -727234320347792652L;

	/** 報案ID*/
	@Column(name = "REG_ID")
	private long regId;
	
	/** 報案編號 */
	@Column(name = "CODE")
	private String code;

	/** 上傳者 */
	@Column(name = "UPLOAD_USER")
	private String uploadUser;

	/** 檔案名稱 */
	@Column(name = "FILE_NAME")
	private String fileName;

	/** 檔案郵件擴展 */
	@Column(name = "FILE_MIME")
	private String fileMime;

	/** 檔案大小 */
	@Column(name = "FILE_SIZE")
	private double fileSize;

	/** 檔案位置 */
	@Column(name = "FILE_URI")
	private String fileUri;

	public Attachment() {
	}

	public Attachment(long regId, String code, String uploadUser,
			String fileName, String fileMime, double fileSize, String fileUri) {
		this.regId = regId;
		this.code = code;
		this.uploadUser = uploadUser;
		this.fileName = fileName;
		this.fileMime = fileMime;
		this.fileSize = fileSize;
		this.fileUri = fileUri;
	}

	public long getRegId() {
		return regId;
	}

	public void setRegId(long regId) {
		this.regId = regId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
		
	public String getUploadUser() {
		return uploadUser;
	}
	
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileMime() {
		return fileMime;
	}

	public void setFileMime(String fileMime) {
		this.fileMime = fileMime;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}	

	@Override
	public String toString() {
		return "Attachment [regId=" + regId + ", code=" + code
				+ ", uploadUser=" + uploadUser + ", fileName=" + fileName
				+ ", fileMime=" + fileMime + ", fileSize=" + fileSize
				+ ", fileUri=" + fileUri + "]";
	}

}
