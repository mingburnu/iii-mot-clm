package org.iii.module.claim.attachment.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.attachment.entity.Attachment;
import org.iii.module.claim.attachment.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 附件管理系統 Action
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/5/20
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AttachmentAction extends GenericCRUDAction<Attachment> {

	/** Upload attributes */
	private File[] myFile;
	private String[] myFileContentType;
	private String[] myFileFileName;

	/** Selected item attributes */
	private String[] selected;

	/** Copy file to a safe location */
	private String destPath = "/iii-mot-clm/mot-clm/src/main/webapp/resources/media/files/";

	private List<Attachment> attachments = new ArrayList<Attachment>();

	@Autowired
	private AttachmentService attachmentService;

	@Override
	public void validateSave() throws Exception {
		String code = String.valueOf(getEntity().getCode());
		File dir = new File(destPath);

		try {
			DataSet<Attachment> ds = attachmentService
					.getByOrderDesc(initDataSet());
			setDs(ds);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}

		if (code.equals("null") == false && code != null
				&& code.isEmpty() == false) {

			if (dir.exists() == false) {
				addActionError("上傳資料夾不存在");
			} else {
				if (myFile == null || myFile.length == 0) {
					addActionError("請選擇檔案");
				} else {
					long sumSize = 0;

					int i = 0;
					while (i < myFile.length) {
						System.out.println("fileSize: " + myFile[i].length());
						sumSize = sumSize + myFile[i].length();
						i++;
					}

					System.out.println("sumSize: " + sumSize);

					if (sumSize <= 20971520) {
						int j = 0;
						while (j < myFile.length) {
							if (myFileFileName[j].contains(".") == false
									|| myFileFileName[j]
											.substring(
													myFileFileName[j]
															.lastIndexOf(".") + 1,
													myFileFileName[j].length())
											.length() == 0) {
								addActionError("格式錯誤");
							}
							j++;
						}
					} else {
						addActionError("一次上傳限制20 Mb以內");
					}
				}
			}
		} else {
			addActionError("請從報案或賠案頁面進入");
		}
	}

	@Override
	public void validateUpdate() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void validateDelete() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public String query() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		try {
			DataSet<Attachment> ds = attachmentService
					.getByOrderDesc(initDataSet());
			setDs(ds);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return "view";
	}

	@Override
	public String save() throws Exception {
		int j = 0;
		while (j < myFile.length) {
			try {
				String[] myFileFileNameSplit1 = myFileFileName[j]
						.split("\\.(?=[^\\.]+$)");
				String firstString = myFileFileNameSplit1[0];
				String extension = myFileFileNameSplit1[1];
				String[] myFileFileNameSplit2 = firstString.split("(?=\\d*$)",
						2);

				File destFile = new File(destPath, myFileFileName[j]);
				if (destFile.exists()) {
					if (firstString.contains("-")) {
						if (myFileFileNameSplit2[1].isEmpty()
								|| myFileFileNameSplit2[1] == null) {
							int i = 0;
							while (destFile.exists()) {
								destFile = new File(destPath, firstString + i
										+ "." + extension);
								i++;
							}
						} else {
							String endNum = myFileFileNameSplit2[1];
							int num = Integer.parseInt(endNum);
							int i = 0;
							while (destFile.exists()) {
								int index = num + i;
								destFile = new File(destPath,
										myFileFileNameSplit2[0] + index + "."
												+ extension);
								i++;
							}
						}
					} else {
						int i = 0;
						while (destFile.exists()) {
							destFile = new File(destPath, firstString + "-" + i
									+ "." + extension);
							i++;
						}
					}
				}

				double kilobyte = (double) myFile[j].length() / 1024;

				String regId = getRequest().getParameter("entity.regId");
				long regIdNum = Long.parseLong(regId);

				Attachment attachment = getEntity();
				attachments.add(new Attachment(regIdNum, getRequest()
						.getParameter("entity.code"), getRequest()
						.getParameter("entity.uploadUser"), myFileFileName[j],
						myFileContentType[j],
						Math.round(kilobyte * 100.0) / 100.0, destFile
								.getName()));
				attachment = attachmentService.save(attachments.get(j),
						getLoginUser());

				// Attachment[] attachmentss = new Attachment[myFile.length];
				// attachmentss[j] = new Attachment(regIdNum,
				// getRequest().getParameter(
				// "entity.code"), getRequest().getParameter(
				// "entity.uploadUser"), myFileFileName[j],
				// myFileContentType[j],
				// Math.round(kilobyte * 100.0) / 100.0,
				// destFile.getName());
				// attachment = attachmentService.save(attachmentss[j],
				// getLoginUser());

				setEntity(attachment);

				FileUtils.copyFile(myFile[j], destFile);

			} catch (IOException e) {
				e.printStackTrace();
				return ERROR;
			} catch (Exception e) {
				log.error(ExceptionUtils.getStackTrace(e));
				throw new Exception(e);
			}
			j++;
		}

		DataSet<Attachment> ds = attachmentService
				.getByOrderDesc(initDataSet());
		setDs(ds);
		addActionMessage("已上傳");
		return EDIT;
	}

	@Override
	public String update() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		String fileUri = getRequest().getParameter("entity.fileUri");
		File destFile = new File(destPath + fileUri);
		destFile.delete();

		try {
			attachmentService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		addActionMessage("檔案已刪除");
		return EDIT;
	}

	public String deleteSelected() throws Exception {
		List<Long> idList = new ArrayList<Long>();

		int i = 0;
		while (i < selected.length) {
			if (NumberUtils.isDigits(selected[i])) {
				idList.add(Long.parseLong(selected[i]));
			}
			i++;
		}

		System.out.println("idList.size(): " + idList.size());

		if (idList.size() != 0) {
			List<Attachment> selList = attachmentService
					.selByIdByCriteria(idList);
			int k = 0;
			while (k < selList.size()) {
				String fileUri = selList.get(k).getFileUri();
				File destFile = new File(destPath + fileUri);
				destFile.delete();
				try {
					attachmentService.deleteById(selList.get(k).getId());
				} catch (Exception e) {
					log.error(ExceptionUtils.getStackTrace(e));
					throw new Exception(e);
				}

				System.out.print("break-------");
				setEntity(null);
				k++;
			}
			addActionMessage("選擇檔案已刪除");
		} else {
			addActionError("請選擇檔案");
		}
		return EDIT;
	}

	/** get By DataSet, only delete 20 records */
	public String deletePartial() throws Exception {
		DataSet<Attachment> allDs = attachmentService.getDsById(initDataSet());
		List<Attachment> allResult = allDs.getResults();

		List<Long> idList = new ArrayList<Long>();

		int k = 0;
		while (k < allResult.size()) {
			idList.add(allResult.get(k).getId());
			k++;
		}

		System.out.println("idList: " + idList);

		List<Attachment> finalResults = new ArrayList<Attachment>();

		String code = getRequest().getParameter("entity.code");
		String regIdString = getRequest().getParameter("entity.regId");
		long regId = Long.parseLong(regIdString);

		int j = 0;
		while (j < idList.size()) {
			if (attachmentService.getById(idList.get(j)).getCode().equals(code)
					&& attachmentService.getById(idList.get(j)).getRegId() == regId) {
				finalResults.add(attachmentService.getById(idList.get(j)));
			}
			j++;
		}
		System.out.println("finalResults Size: " + finalResults.size());
		System.out.println("finalResults List: " + finalResults);

		int i = 0;
		while (i < finalResults.size()) {
			String fileUri = finalResults.get(i).getFileUri();
			File destFile = new File(destPath + fileUri);
			destFile.delete();
			try {
				attachmentService.deleteById(finalResults.get(i).getId());
			} catch (Exception e) {
				log.error(ExceptionUtils.getStackTrace(e));
				throw new Exception(e);
			}
			setEntity(null);
			i++;
		}

		addActionMessage("20筆檔案刪除");
		return EDIT;
	}

	public String deleteAll() throws Exception {
		List<Long> idList = attachmentService.queryAllIdByHql();
		List<Attachment> AllResults = new ArrayList<Attachment>();

		String code = getRequest().getParameter("entity.code");
		String regIdString = getRequest().getParameter("entity.regId");
		long regId = Long.parseLong(regIdString);

		int j = 0;
		while (j < idList.size()) {
			if (attachmentService.getById(idList.get(j)).getCode().equals(code)
					&& attachmentService.getById(idList.get(j)).getRegId() == regId) {
				AllResults.add(attachmentService.getById(idList.get(j)));
			}
			j++;
		}

		int i = 0;
		while (i < AllResults.size()) {
			String fileUri = AllResults.get(i).getFileUri();
			File destFile = new File(destPath + fileUri);
			destFile.delete();
			try {
				attachmentService.deleteById(AllResults.get(i).getId());
			} catch (Exception e) {
				log.error(ExceptionUtils.getStackTrace(e));
				throw new Exception(e);
			}
			setEntity(null);
			i++;
		}

		addActionMessage("檔案全部刪除");
		return EDIT;
	}

	public File[] getMyFile() {
		return myFile;
	}

	public void setMyFile(File[] myFile) {
		this.myFile = myFile;
	}

	public String[] getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String[] myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String[] getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String[] myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String[] getSelected() {
		return selected;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}

}