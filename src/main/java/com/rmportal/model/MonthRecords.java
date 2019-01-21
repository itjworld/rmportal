package com.rmportal.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

@Entity
@Table(name = "RM_MONTH_RECORDS", uniqueConstraints = { @UniqueConstraint(columnNames = { "month", "year" }) })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "MonthRecords")
@Data(staticConstructor = "of")
public class MonthRecords {

	public MonthRecords(int month, int year) {
		this.year = year;
		this.month = month;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "MONTH")
	private int month;
	@Column(name = "YEAR")
	private int year;

	public static MonthRecordsBuilder builder() {
		return new MonthRecordsBuilder();
	}

	public static class MonthRecordsBuilder {

		private int month;
		private int year;

		public MonthRecordsBuilder month(final int month) {
			this.month = month;
			return this;
		}

		public MonthRecordsBuilder year(final int year) {
			this.year = year;
			return this;
		}

		public MonthRecords build() {
			return new MonthRecords(month, year);
		}

	}

}
