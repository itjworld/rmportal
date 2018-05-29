package com.rmportal.util;

import java.util.Comparator;

import com.rmportal.model.PortalMappingInfo;

public class PortalMappingComparator implements Comparator<PortalMappingInfo> {
	public int compare(PortalMappingInfo a, PortalMappingInfo b) {
		return a.getRoomNumber() - b.getRoomNumber();
	}
}
