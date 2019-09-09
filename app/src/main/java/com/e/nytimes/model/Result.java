
package com.e.nytimes.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result implements Parcelable {
    private String url;
    private String adxKeywords;
    private String column;
    private String section;
    private String byline;
    private String type;
    private String title;
    private String _abstract;
    private String publishedDate;
    private String source;
    private long id;
    private long assetId;
    private int views;
    private List<String> desFacet = null;
    private String orgFacet;
    private String perFacet;
    private List<String> geoFacet = null;
    private List<Medium> media = null;
    private String uri;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.adxKeywords);
        dest.writeString(this.column);
        dest.writeString(this.section);
        dest.writeString(this.byline);
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeString(this._abstract);
        dest.writeString(this.publishedDate);
        dest.writeString(this.source);
        dest.writeLong(this.id);
        dest.writeLong(this.assetId);
        dest.writeInt(this.views);
        dest.writeStringList(this.desFacet);
        dest.writeString(this.orgFacet);
        dest.writeString(this.perFacet);
        dest.writeStringList(this.geoFacet);
        dest.writeList(this.media);
        dest.writeString(this.uri);
    }

    protected Result(Parcel in) {
        this.url = in.readString();
        this.adxKeywords = in.readString();
        this.column = in.readString();
        this.section = in.readString();
        this.byline = in.readString();
        this.type = in.readString();
        this.title = in.readString();
        this._abstract = in.readString();
        this.publishedDate = in.readString();
        this.source = in.readString();
        this.id = in.readLong();
        this.assetId = in.readLong();
        this.views = in.readInt();
        this.desFacet = in.createStringArrayList();
        this.orgFacet = in.readString();
        this.perFacet = in.readString();
        this.geoFacet = in.createStringArrayList();
        this.media = new ArrayList<Medium>();
        in.readList(this.media, Medium.class.getClassLoader());
        this.uri = in.readString();
    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
