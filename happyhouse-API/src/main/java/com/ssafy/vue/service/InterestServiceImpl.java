package com.ssafy.vue.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.Hospital;
import com.ssafy.vue.dto.Interest;
import com.ssafy.vue.dto.Shop;
import com.ssafy.vue.mapper.InterestMapper;


@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	private InterestMapper interestMapper;
	
	@Override
	public List<Interest> selectAllById(String id) throws Exception {
		List<Interest> list = interestMapper.selectAllById(id);
		Collections.sort(list, new Comparator<Interest> () {
			@Override
			public int compare(Interest o1, Interest o2) {
				if(o1.getSidoName().compareTo(o2.getSidoName())==0) {
					return o1.getGugunName().compareTo(o2.getGugunName());
				}
				else {
					return o1.getSidoName().compareTo(o2.getSidoName());
				}
			}
		});
		
		return list;
	}

	@Override
	public Interest selectOne(int idx) throws Exception {
		return interestMapper.selectOne(idx);
	}

	@Override
	public boolean insert(Interest interest) throws Exception {
		return interestMapper.insert(interest) == 1;
	}

	@Override
	public boolean update(Interest interest) throws Exception {
		return interestMapper.update(interest) == 1;
	}

	@Override
	public boolean delete(int idx) throws Exception {
		return interestMapper.delete(idx) == 1;
	}

	@Override
	public List<Hospital> selectHospital(String sidoName, String gugunName) throws Exception {
		StringBuilder sb = new StringBuilder();
		if(sidoName.length() == 4) {
			sb.append(sidoName.charAt(0)).append(sidoName.charAt(2));
			sidoName = sb.toString();
		}
		return interestMapper.selectHospital(sidoName, gugunName);
	}

	@Override
	public List<Shop> selectShop(String sidoName, String gugunName) throws Exception {
		return interestMapper.selectShop(sidoName, gugunName);
	}

	@Override
	public String hospitalRoute(String sidoName, String gugunName) throws Exception {
		List<Hospital> hos = interestMapper.selectHospital(sidoName, gugunName);
		return null;
	}

	@Override
	public String shopRoute(String sidoName, String gugunName) throws Exception {
		List<Shop> shop = interestMapper.selectShop(sidoName, gugunName);
		return null;
	}

	@Override
	public String showHospitalShop(String sidoName, String gugunName) throws Exception {
		List<Hospital> hos = interestMapper.selectHospital(sidoName, gugunName);
		List<Shop> shop = interestMapper.selectShop(sidoName, gugunName);
		double lat, lng, m;
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i = 0; i<hos.size(); i++) {
			for(int j = 0; j<shop.size(); j++) {
				lat = Math.abs(Double.parseDouble((hos.get(i).getLat())) - Double.parseDouble((shop.get(j).getLng())));
				lat = Math.round(lat*1000)/1000.0;
				lng = Math.abs(Double.parseDouble((hos.get(i).getLng())) - Double.parseDouble((shop.get(j).getLat())));
				lng = Math.round(lng*1000)/1000.0;
				m = Math.pow(lat*((double)133330), 2) + Math.pow(lng*((double)133200), 2);
				m = Math.sqrt(m);
				pq.add(new Node(i, j, m));
			}
		}
		for(int i = 0; i<3; i++) {
			if(pq.isEmpty()) break;
			Node node = pq.poll();
			sb.append(i+1).append(". ").append(hos.get(node.i).getHospitalName()).append(" ").append(shop.get(node.j).getShopName()).append(" ").append(node.cost).append("m\\n");
		}
		return sb.toString();
	}
	
	static class Node implements Comparable<Node>{
		int i, j;
		double cost;

		Node(int i, int j, double cost) {
			this.i = i;
			this.j = j;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost > o.cost ? 1 : -1;
		}
	}

}
