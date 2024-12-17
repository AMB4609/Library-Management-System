import { HttpInterceptorFn } from '@angular/common/http';

export const customInterceptor: HttpInterceptorFn = (req, next) => {

  debugger;
  const localToken = localStorage.getItem('token');
  console.log(localToken);
  const excludedEndpoints = ['/login', '/register','/getAll','getBookById/1']; // List of endpoints to exclude

  // Check if the request URL matches any excluded endpoint
  for (const endpoint of excludedEndpoints) {
    if (req.url.includes(endpoint)) {
      return next(req);
    }
  }

  // If not excluded and token exists, attach Authorization header
  if (localToken) {
    debugger;
    const clonedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${localToken}`,
      },

    });
    // console.log(Headers)
    return next(clonedReq);
  }

  return next(req);
};
