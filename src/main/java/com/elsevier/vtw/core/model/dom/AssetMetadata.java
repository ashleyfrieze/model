package com.elsevier.vtw.core.model.dom;

import com.elsevier.vtw.core.model.ContentObjectMetadata;
import org.joda.time.DateTime;

public class AssetMetadata {

	private final String type;
	private final String id;
	private final DateTime createdOn;
	private final int sequence;
	private final int byteCount;
	private final boolean isMultipart;
	private final String mode;
	private final String isFormatOf;
	private final String filename;
	private final String version;
	private final String assetType;
	private final String format;
	
	private AssetMetadata(String type, String id, String format,
			String assetType, String version, String filename,
			String isFormatOf, String mode, boolean isMultipart,
			int byteCount, int sequence, DateTime createdOn) {

		this.type = type;
		this.id = id;
		this.format = format;
		this.assetType = assetType;
		this.version = version;
		this.filename = filename;
		this.isFormatOf = isFormatOf;
		this.mode = mode;
		this.isMultipart = isMultipart;
		this.byteCount = byteCount;
		this.sequence = sequence;
		this.createdOn = createdOn;
	}
	
	public String getId() {
		return id;
	}

	public DateTime getCreatedOn() {
		return createdOn;
	}

	public int getSequence() {
		return sequence;
	}

	public int getByteCount() {
		return byteCount;
	}

	public boolean isMultipart() {
		return isMultipart;
	}

	public String getMode() {
		return mode;
	}

	public String getIsFormatOf() {
		return isFormatOf;
	}

	public String getFilename() {
		return filename;
	}

	public String getVersion() {
		return version;
	}

	public String getAssetType() {
		return assetType;
	}

	public String getFormat() {
		return format;
	}

	public String getType() {return type; }

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		
		private ContentObjectMetadata.Builder parent;
		private String type;
		private String id;
		private String format;
		private String assetType;
		private String version;
		private String filename;
		private String isFormatOf;
		private String mode;
		private boolean isMultipart;
		private int byteCount;
		private int sequence;
		private DateTime createdOn;

		public Builder() {
		}
		
		public Builder(ContentObjectMetadata.Builder parent) {
			this.parent = parent;
		}

		public AssetMetadata build() {

			return new AssetMetadata(this.type,
					this.id,
					this.format,
					this.assetType,
					this.version,
					this.filename,
					this.isFormatOf,
					this.mode,
					this.isMultipart,
					this.byteCount,
					this.sequence,
					this.createdOn);
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder format(String format) {
			this.format = format;
			return this;
		}

		public Builder assetType(String assetType) {
			this.assetType = assetType;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Builder filename(String filename) {
			this.filename = filename;
			return this;
		}

		public Builder isFormatOf(String isFormatOf) {
			this.isFormatOf = isFormatOf;
			return this;
		}

		public Builder mode(String mode) {
			this.mode = mode;
			return this;
		}

		public Builder isMultipart(boolean b) {
			this.isMultipart = b;
			return this;
		}

		public Builder byteCount(int i) {
			this.byteCount = i;
			return this;
		}

		public Builder sequence(int i) {
			this.sequence = i;
			return this;
		}

		public Builder createdOn(DateTime dateTime) {
			this.createdOn = dateTime;
			return this;
		}

		public ContentObjectMetadata.Builder insert() {
			AssetMetadata am = build();
			return parent.withAssetMetadata(am);
		}
	}

	
}



