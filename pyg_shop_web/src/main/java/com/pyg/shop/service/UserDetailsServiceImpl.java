package com.pyg.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.pojo.TbSeller;
import com.pyg.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private SellerService sellerService;


	public SellerService getSellerService() {
		return sellerService;
	}


	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> grandtAuths=new ArrayList();
		grandtAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		TbSeller seller = sellerService.findOne(username);
		if(seller!=null) {
			if(seller.getStatus().equals("1")) {
				return new User(username, seller.getPassword(),grandtAuths);
			}else {
				return null;
			}
		}else {
			
			return null;
		}
	}

}
