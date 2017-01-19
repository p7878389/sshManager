package com.manage.service.imp;

import com.manage.dao.Imp.ResourceDaoImpl;
import com.manage.entity.Resource;
import com.manage.service.ResourceService;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * 菜单、按钮资源
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDaoImpl resourceDao;

    @Autowired
    private UserService userService;

    @Override
    public Resource getResource(final Integer id) {
        return resourceDao.findById( id );
    }


    @Override
    public void updateResource(final Resource resource) {
        resourceDao.update( resource );
    }


    /**
     * 用户菜单初始化
     *
     * @param id
     * @return
     */
    @Override
    public List<Resource> initMenu(final int id) {
        //List<Resource> resources = new ArrayList<Resource>();

//        User user = userService.findById(id);
//        Set urSet = user.getUserroles();
//        Iterator setUrIt = urSet.iterator();
//        while (setUrIt.hasNext()) {
//            Userrole userrole = (Userrole) setUrIt.next();
//            Role role = userrole.getRole();
//            Set resouSet = role.getRoleresources();
//            Iterator setresouIt = resouSet.iterator();
//            while (setresouIt.hasNext()) {
//                Resource resource = (Resource) setresouIt.next();
//                if (resource.getType() != MENU_TYPE) {
//                    continue;
//                }
//                if (!resources.contains(resource)) {
//                    resources.add(resource);
//                }
//            }
//        }
        List<Resource> resourceList = resourceDao.findAll( new Resource() );
        Iterator it = resourceList.iterator();
        while (it.hasNext()) {
            Resource r = (Resource) it.next();
            if (r != null && r.getType() != null) {
                if (!MENU_TYPE.equals( r.getType() )) {
                    it.remove();
                }
            }
        }
        return resourceList;
    }

    @Override
    public List<Resource> findAllResource() {
        return resourceDao.findAll( new Resource() );
    }

    public ResourceDaoImpl getResourceDao() {
        return resourceDao;
    }

    public void setResourceDao(ResourceDaoImpl resourceDao) {
        this.resourceDao = resourceDao;
    }
}
