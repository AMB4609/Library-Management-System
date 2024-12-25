import { HttpInterceptorFn } from '@angular/common/http';

export const customInterceptor: HttpInterceptorFn = (req, next) => {

  ;
  const localToken = localStorage.getItem('token');
  (localToken);
  const excludedEndpoints = ['/login','/getAll','getBookById/1','/getAllBranches','/getBranchById/']; // List of endpoints to exclude

  // Check if the request URL matches any excluded endpoint
  for (const endpoint of excludedEndpoints) {
    if (req.url.includes(endpoint)) {
      return next(req);
    }
  }

  // If not excluded and token exists, attach Authorization header
  if (localToken) {
    ;
    const clonedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${localToken}`,
      },

    });
    // (Headers)
    return next(clonedReq);
  }

  return next(req);
};
