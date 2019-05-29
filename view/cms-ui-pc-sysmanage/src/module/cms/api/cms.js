import http from "../../../base/api/public";
import querystring from "querystring";

let sysConfig = require('@/../config/sysConfig');
let apiUrl = sysConfig.cmsApiUrlPre;

export const page_list = (page, size, params) => {
  let query = querystring.stringify(params);
  return http.requestQuickGet(apiUrl + "/cms/page/list/" + page + "/" + size + "?" + query);
};
export const page_add = page => {
  return http.requestPost(apiUrl + "/cms/page/add", page);
};
export const page_get = pageId => {
  return http.requestGet(apiUrl + "/cms/page/get/" + pageId);
};
export const page_update = (pageId, page) => {
  return http.requestPut(apiUrl + "/cms/page/update/" + pageId, page);
};
export const page_delete = (pageId) => {
  return http.requestDelete(apiUrl + "/cms/page/del/" + pageId);
};
export const page_release = (pageId) => {
  return http.requestPost(apiUrl + "/cms/page/release/" + pageId)
};

// General
export const sub_site_list = (siteName) => {
  let query = querystring.stringify({siteName});
  return http.requestQuickGet(apiUrl + "/cms/site/sublist?" + query);
};
export const sub_template_list = (siteId, templateName) => {
  let query = querystring.stringify({siteId, "query": templateName});
  return http.requestQuickGet(apiUrl + "/cms/template/sublist?" + query);
};
