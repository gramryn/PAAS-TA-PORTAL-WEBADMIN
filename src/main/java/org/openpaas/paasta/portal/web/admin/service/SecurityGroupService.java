package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Service
public class SecurityGroupService extends Common {

    private final String V2_URL = "/v2";

    @Autowired
    CommonService commonService;

    /**
     * 시큐리티 그룹 메인 화면
     *
     * @return model and view
     */
    public ModelAndView getSecurityGroupMain() {
        return new ModelAndView(){{setViewName("securityGroup/securityGroupMain");}};
    }

    /**
     * 시큐리티 그룹 리스트를 가져온다.
     *
     * @param page  SecurityGroup List page
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    public Map<String, Object> getSecurityGroupList(String page)  throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroups/" + page, HttpMethod.GET, null,this.getToken());
    }


    /**
     * 시큐리티그룹을 상세조회한다.
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     */
    public Map<String, Object> getSecurityGroupResponse(String securityid) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+ "/securitygroup/" + securityid, HttpMethod.GET, null , this.getToken());
    }


    /**
     * 시큐리티그룹을 생성한다.
     *
     * @param groupname  the SecurityGroup Name
     * @param rule the Object
     * @return Map<String, Object>
     * @throws Exception the exception
     * @author 박철한
     */
    public Map<String, Object> createSecurityGroupResponse(@PathVariable String groupname, @RequestBody Object rule) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+groupname, HttpMethod.POST, rule , this.getToken());
    }

    /**
     * 시큐리티그룹을 수정한다.
     *
     * @param securityid  the security guid
     * @param groupname  the SecurityGroup Name
     * @param rule the Object
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    public Map<String, Object> updateSecurityGroupResponse(String securityid, String groupname, Object rule) throws Exception {
        System.out.println(rule);
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+securityid+"/"+groupname, HttpMethod.PUT, rule , this.getToken());
    }


    /**
     * 시큐리티그룹을 삭제한다.
     *
     * @param securityid  the security guid
     * @return Map<String, Object>
     * @throws Exception the exception
     * @author 박철한
     */
    public Map<String, Object> deleteSecurityGroupResponse(@PathVariable String securityid) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+securityid, HttpMethod.DELETE, null , this.getToken());
    }

    /**
     * 준비 응용 프로그램에 사용할 보안 그룹 목록에 보안 그룹을 바인딩합니다.
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @PutMapping(Constants.V2_URL+"/securitygroup/{securityid}/staging")
    public Map<String, Object> setSecurityGroupStagingDefaultResponse(@PathVariable String securityid) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+securityid+"/staging", HttpMethod.PUT, null , this.getToken());
    }


    /**
     * 시큐리티 스테이징 그룹을 조회한다.
     *
     * @param page  listStagingDefaults page
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    public Map<String, Object>  listSecurityGroupStagingDefaultsResponse(int page) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+page+"/staging", HttpMethod.GET, null , this.getToken());
    }


    /**
     * 스테이징 시큐리티 그룹을 언바인드 한다.
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    public Map<String, Object> removeSecurityGroupStaging(String securityid)  throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+securityid+"/staging", HttpMethod.DELETE, null , this.getToken());
    }


    /**
     *
     * 응용 프로그램 실행에 사용할 보안 그룹 목록에 보안 그룹을 바인딩합니다.
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    public Map<String, Object> setSecurityGroupRunningDefaultResponse(@PathVariable String securityid) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+securityid+"/running", HttpMethod.PUT, null , this.getToken());
    }


    /**
     * 실행중인 응용 프로그램에 대한 시큐리티 그룹 조회
     *
     * @param page  ListSecurityGroups page
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    public Map<String, Object> listSecurityGroupRunningDefaultsResponse(@PathVariable int page) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+page+"/running", HttpMethod.GET, null , this.getToken());
    }


    /**
     * 실행중인 응용 프로그램에 대한 시큐리티 그룹 언바인드
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    public Map<String, Object> removeSecurityGroupRunning(@PathVariable String securityid) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+securityid+"/running", HttpMethod.DELETE, null , this.getToken());
    }

    /**
     * 공간 시큐리티그룹을 조회한다.
     *
     * @param page  ListSecurityGroups page
     * @param securityid the security guid
     * @return ListSecurityGroupSpacesResponse
     * @throws Exception the exception
     */
    public Map<String, Object> listSecurityGroupSpacesResponse(@PathVariable String securityid, @PathVariable int page) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+securityid+"/" + page, HttpMethod.GET, null , this.getToken());
    }

    /**
     * 시큐리티그룹과 공간을 연결한다.
     *
     * @param securityid  the security guid
     * @param spaceid the space guid
     * @return AssociateSecurityGroupSpaceResponse
     * @throws Exception the exception
     */
    public Map<String, Object> associateSecurityGroupSpaceResponse(@PathVariable String securityid, @PathVariable String spaceid) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+securityid+"/spaces/" + spaceid, HttpMethod.PUT, null , this.getToken());
    }

    /**
     * 공간 시큐리티 그룹을 언바인드 한다.
     *
     * @param securityid  the security guid
     * @param spaceid the space guid
     * @return Map
     * @throws Exception the exception
     */
    public Map<String, Object> removeSecurityGroupSpace(@PathVariable String securityid, @PathVariable String spaceid) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL+"/securitygroup/"+securityid+"/spaces/" + spaceid, HttpMethod.DELETE, null , this.getToken());
    }
}
