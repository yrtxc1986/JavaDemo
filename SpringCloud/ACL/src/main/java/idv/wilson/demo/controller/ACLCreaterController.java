package idv.wilson.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.wilson.demo.entity.DMSDocument;
import idv.wilson.demo.repository.DMSDocumentRepository;
import idv.wilson.demo.repository.DMSIndexRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/acl")
@Slf4j
public class ACLCreaterController {

	@Autowired
	private MutableAclService aclService;

	@GetMapping()
	public List<AccessControlEntry> getACL() {
		ObjectIdentity oi = new ObjectIdentityImpl(DMSDocument.class, 3);
		Acl acl = aclService.readAclById(oi);
		MutableAcl mAcl = (MutableAcl) acl;
		LinkedList<String> PermissionString = new LinkedList<String>();
		for (AccessControlEntry e : mAcl.getEntries()) {
			PermissionString.push(e.getSid() + ":" + e.getPermission());
			log.info(e.getSid() + ":" + e.getPermission());
		}
		return mAcl.getEntries();
		// return acl.getEntries();

		// return acl;
	}

	@GetMapping("/new")
	public String createAccount() {
		DMSDocument document = new DMSDocument();
		document.setId(3);
		ObjectIdentity oi = new ObjectIdentityImpl(DMSDocument.class, document.getId());
		MutableAcl acl = aclService.createAcl(oi);
		aclService.updateAcl(acl);
		return "";
	}

	@GetMapping("/setGroup")
	public String setGroup() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		ObjectIdentity oi = new ObjectIdentityImpl(DMSDocument.class, 3);
		MutableAcl acl = (MutableAcl) aclService.readAclById(oi);
		Sid sid = new PrincipalSid(name);
		// Sid sid = new GrantedAuthoritySid("ROLE_ADMIN");
		acl.insertAce(acl.getEntries().size(), BasePermission.READ, sid, true);
		aclService.updateAcl(acl);
		return "";
	}

	@GetMapping("/list")
	public List<DMSDocument> ListDOC() {
		return DMSDoc.findAll();
	}

	@Autowired
	DMSDocumentRepository DMSDoc;

	@GetMapping("/detail")
	public Authentication getUserDetail() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@GetMapping("/addAuthorities")
	public void addAuthorities() {
		Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ANOTHER");
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		updatedAuthorities.add(authority);
		updatedAuthorities.addAll(oldAuthorities);

		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
						SecurityContextHolder.getContext().getAuthentication().getCredentials(), updatedAuthorities));
	}

	@Autowired
	DMSIndexRepository DMSIndex;

	@GetMapping("/listindex")
	public List<idv.wilson.demo.entity.DMSIndex> listINDEX() {
		return DMSIndex.findAll();
	}

}
