package com.example.demo.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class BaseEntity {

	private Timestamp createdAt;

	private Timestamp updatedAt;

	public BaseEntity() {
		if (getCreatedAt() == null) {
			setCreatedAt(Timestamp.from(Instant.now()));
		}
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	// legacy code
	private void setUpdatedAtTimestamp(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setUpdatedAt(Instant instant) {
		if (instant == null) {
			setUpdatedAtTimestamp(null);
		} else {
			setUpdatedAtTimestamp(Timestamp.from(instant.truncatedTo(ChronoUnit.MILLIS)));
		}
	}

	// legacy code
	public void updateUpdatedAt() {
		setUpdatedAt(Instant.now());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseEntity [createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append("]");
		return builder.toString();
	}

}
