package com.atguigu.crm.orm;

import java.util.List;

public class Page<T> {

	private int pageNo;
	private int pageSize = 5;

	private long totalElements;
	private List<T> content;

	// 获取下一页的页码
	public int getNext() {
		if (isHasNext()) {
			return this.pageNo + 1;
		}
		return this.pageNo;
	}

	// 获取上一页的页码
	public int getPrev() {
		if (isHasPrev()) {
			return this.pageNo - 1;
		}
		return this.pageNo;
	}

	// 是否有下一页
	public boolean isHasNext() {
		return this.pageNo < getTotalPages();
	}

	// 是否有上一页
	public boolean isHasPrev() {
		return this.pageNo > 1;
	}

	// 获取总页数
	public int getTotalPages() {
		int totalPages = (int) (this.totalElements / this.pageSize);

		if (this.totalElements % this.pageSize != 0) {
			totalPages++;
		}

		return totalPages;
	}

	public int getPageNo() {
		return pageNo;
	}

	// 对pageNo进行合法性验证
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;

		if (this.pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;

		// 校验pageNo合法性
		if (this.pageNo > getTotalPages()) {
			this.pageNo = getTotalPages();
		}
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
