function mvp_ap(){var bb='',$=' top: -1000px;',yb='" for "gwt:onLoadErrorFn"',wb='" for "gwt:onPropertyErrorFn"',hb='");',zb='#',_b='.cache.js',Bb='/',Hb='//',Wb='284C4DDA113E3373771955F7DB21D280',Xb='934516C9907CA008D7DD91A735DC71C3',$b=':',qb='::',gc=':moduleBase',ab='<!doctype html>',cb='<html><head><\/head><body><\/body><\/html>',tb='=',Ab='?',Yb='BDF99FCEC0E7B94A5D0F87E393EF8630',vb='Bad handler "',Zb='C30081D2469AEC7E0F738F759D3FF505',_='CSS1Compat',fb='Chrome',eb='DOMContentLoaded',V='DUMMY',fc='Ignoring non-whitelisted Dev Mode URL: ',ec='__gwtDevModeHook:mvp_ap',Mb='android',Gb='base',Eb='baseUrl',Q='begin',Qb='blackberry',W='body',P='bootstrap',Db='clear.cache.gif',sb='content',bc='end',gb='eval("',dc='file:',Rb='file://',R='gwt.codesvr.mvp_ap=',S='gwt.codesvr=',xb='gwt:onLoadErrorFn',ub='gwt:onPropertyErrorFn',rb='gwt:property',mb='head',cc='http:',X='iframe',Cb='img',Nb='ipad',Pb='iphone',Ob='ipod',jb='javascript',Y='javascript:""',ac='loadExternalRefs',nb='meta',Ib='mobile.user.agent',Jb='mobilesafari',lb='moduleRequested',kb='moduleStartup',T='mvp_ap',Vb='mvp_ap.devmode.js',Fb='mvp_ap.nocache.js',pb='mvp_ap::',ob='name',Tb='no',Kb='not_mobile',Lb='phonegap.env',Z='position:absolute; width:0; height:0; border:none; left: -1000px;',ib='script',Ub='selectingPermutation',U='startup',db='undefined',Sb='yes';var o=window;var p=document;r(P,Q);function q(){var a=o.location.search;return a.indexOf(R)!=-1||a.indexOf(S)!=-1}
function r(a,b){if(o.__gwtStatsEvent){o.__gwtStatsEvent({moduleName:T,sessionId:o.__gwtStatsSessionId,subSystem:U,evtGroup:a,millis:(new Date).getTime(),type:b})}}
mvp_ap.__sendStats=r;mvp_ap.__moduleName=T;mvp_ap.__errFn=null;mvp_ap.__moduleBase=V;mvp_ap.__softPermutationId=0;mvp_ap.__computePropValue=null;mvp_ap.__getPropMap=null;mvp_ap.__gwtInstallCode=function(){};mvp_ap.__gwtStartLoadingFragment=function(){return null};var s=function(){return false};var t=function(){return null};__propertyErrorFunction=null;var u=o.__gwt_activeModules=o.__gwt_activeModules||{};u[T]={moduleName:T};var v;function w(){B();return v}
function A(){B();return v.getElementsByTagName(W)[0]}
function B(){if(v){return}var a=p.createElement(X);a.src=Y;a.id=T;a.style.cssText=Z+$;a.tabIndex=-1;p.body.appendChild(a);v=a.contentDocument;if(!v){v=a.contentWindow.document}v.open();var b=document.compatMode==_?ab:bb;v.write(b+cb);v.close()}
function C(k){function l(a){function b(){if(typeof p.readyState==db){return typeof p.body!=db&&p.body!=null}return /loaded|complete/.test(p.readyState)}
var c=b();if(c){a();return}function d(){if(!c){c=true;a();if(p.removeEventListener){p.removeEventListener(eb,d,false)}if(e){clearInterval(e)}}}
if(p.addEventListener){p.addEventListener(eb,d,false)}var e=setInterval(function(){if(b()){d()}},50)}
function m(c){function d(a,b){a.removeChild(b)}
var e=A();var f=w();var g;if(navigator.userAgent.indexOf(fb)>-1&&window.JSON){var h=f.createDocumentFragment();h.appendChild(f.createTextNode(gb));for(var i=0;i<c.length;i++){var j=window.JSON.stringify(c[i]);h.appendChild(f.createTextNode(j.substring(1,j.length-1)))}h.appendChild(f.createTextNode(hb));g=f.createElement(ib);g.language=jb;g.appendChild(h);e.appendChild(g);d(e,g)}else{for(var i=0;i<c.length;i++){g=f.createElement(ib);g.language=jb;g.text=c[i];e.appendChild(g);d(e,g)}}}
mvp_ap.onScriptDownloaded=function(a){l(function(){m(a)})};r(kb,lb);var n=p.createElement(ib);n.src=k;p.getElementsByTagName(mb)[0].appendChild(n)}
mvp_ap.__startLoadingFragment=function(a){return G(a)};mvp_ap.__installRunAsyncCode=function(a){var b=A();var c=w().createElement(ib);c.language=jb;c.text=a;b.appendChild(c);b.removeChild(c)};function D(){var c={};var d;var e;var f=p.getElementsByTagName(nb);for(var g=0,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(ob),k;if(j){j=j.replace(pb,bb);if(j.indexOf(qb)>=0){continue}if(j==rb){k=i.getAttribute(sb);if(k){var l,m=k.indexOf(tb);if(m>=0){j=k.substring(0,m);l=k.substring(m+1)}else{j=k;l=bb}c[j]=l}}else if(j==ub){k=i.getAttribute(sb);if(k){try{d=eval(k)}catch(a){alert(vb+k+wb)}}}else if(j==xb){k=i.getAttribute(sb);if(k){try{e=eval(k)}catch(a){alert(vb+k+yb)}}}}}t=function(a){var b=c[a];return b==null?null:b};__propertyErrorFunction=d;mvp_ap.__errFn=e}
function F(){function e(a){var b=a.lastIndexOf(zb);if(b==-1){b=a.length}var c=a.indexOf(Ab);if(c==-1){c=a.length}var d=a.lastIndexOf(Bb,Math.min(c,b));return d>=0?a.substring(0,d+1):bb}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=p.createElement(Cb);b.src=a+Db;a=e(b.src)}return a}
function g(){var a=t(Eb);if(a!=null){return a}return bb}
function h(){var a=p.getElementsByTagName(ib);for(var b=0;b<a.length;++b){if(a[b].src.indexOf(Fb)!=-1){return e(a[b].src)}}return bb}
function i(){var a=p.getElementsByTagName(Gb);if(a.length>0){return a[a.length-1].href}return bb}
function j(){var a=p.location;return a.href==a.protocol+Hb+a.host+a.pathname+a.search+a.hash}
var k=g();if(k==bb){k=h()}if(k==bb){k=i()}if(k==bb&&j()){k=e(p.location.href)}k=f(k);return k}
function G(a){if(a.match(/^\//)){return a}if(a.match(/^[a-zA-Z]+:\/\//)){return a}return mvp_ap.__moduleBase+a}
function H(){var f=[];var g;function h(a,b){var c=f;for(var d=0,e=a.length-1;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
var i=[];var j=[];function k(a){var b=j[a](),c=i[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(__propertyErrorFunc){__propertyErrorFunc(a,d,b)}throw null}
j[Ib]=function(){return /(android|iphone|ipod|ipad)/i.test(window.navigator.userAgent)?Jb:Kb};i[Ib]={mobilesafari:0,not_mobile:1};j[Lb]=function(){{var a=window.navigator.userAgent.toLowerCase();if(a.indexOf(Mb)!=-1||(a.indexOf(Nb)!=-1||(a.indexOf(Ob)!=-1||(a.indexOf(Pb)!=-1||a.indexOf(Qb)!=-1)))){var b=document.location.href;if(b.indexOf(Rb)===0){return Sb}}return Tb}};i[Lb]={no:0,yes:1};s=function(a,b){return b in i[a]};mvp_ap.__getPropMap=function(){var a={};for(var b in i){if(i.hasOwnProperty(b)){a[b]=k(b)}}return a};mvp_ap.__computePropValue=k;o.__gwt_activeModules[T].bindings=mvp_ap.__getPropMap;r(P,Ub);if(q()){return G(Vb)}var l;try{h([Kb,Sb],Wb);h([Jb,Sb],Xb);h([Jb,Tb],Yb);h([Kb,Tb],Zb);l=f[k(Ib)][k(Lb)];var m=l.indexOf($b);if(m!=-1){g=parseInt(l.substring(m+1),10);l=l.substring(0,m)}}catch(a){}mvp_ap.__softPermutationId=g;return G(l+_b)}
function I(){if(!o.__gwt_stylesLoaded){o.__gwt_stylesLoaded={}}r(ac,Q);r(ac,bc)}
D();mvp_ap.__moduleBase=F();u[T].moduleBase=mvp_ap.__moduleBase;var J=H();if(o){var K=!!(o.location.protocol==cc||o.location.protocol==dc);o.__gwt_activeModules[T].canRedirect=K;if(K){var L=ec;var M=o.sessionStorage[L];if(!/^http:\/\/(localhost|127\.0\.0\.1)(:\d+)?\/.*$/.test(M)){if(M&&(window.console&&console.log)){console.log(fc+M)}M=bb}if(M&&!o[L]){o[L]=true;o[L+gc]=F();var N=p.createElement(ib);N.src=M;var O=p.getElementsByTagName(mb)[0];O.insertBefore(N,O.firstElementChild||O.children[0]);return false}}}I();r(P,bc);C(J);return true}
mvp_ap.succeeded=mvp_ap();