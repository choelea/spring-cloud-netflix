package com.joe.bookmark.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BookmarkData {
	@ApiModelProperty(value="userid which identify the owner of this bookmark",example="choelea")
    private String userId;

	@ApiModelProperty(value="Identifier of this bookmark")
	private Long id;
	 
	@ApiModelProperty(value="link of the bookmark",example="https://www.okchem.com")
    private String href;

	@ApiModelProperty(value="Desscription of this bookmark")
    private String description;

    public BookmarkData(String userId, String href,
                    String description, String label) {
        this.userId = userId;
        this.href = href;
        this.description = description;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public String getHref() {
        return href;
    }

    public String getDescription() {
        return description;
    }

    private String label;
}
