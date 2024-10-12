"use client";

import React, { createContext, useState, ReactNode } from "react";

// **********************************************************************
// *** 사용하는 아이디 리스트 ***
// capPct24H - 시가총액 변화율(24h)
// capAvg - 시가총액 평균치
// activeCrypto - 활성화된 크립트
// updatedDt - API 마지막 요청 날짜

// capChange - 시가총액 변화율 차트
// totalVolume - 총 거래량 TOP
// capPct -  시가총액 비율
// searchTrend - 인기 있는 검색코인(24h)
// **********************************************************************

interface DepositContextProps {
  contextValue: { [key: string]: any };
  setContextValue: (id: string, value: any) => void;
}

// 페이지에서 임포트해서 사용할 모듈
export const ContextModule = createContext<DepositContextProps | undefined>(
  undefined,
);

export const ContextProvider: React.FC<{ children: ReactNode }> = ({
  children,
}) => {
  const [contextValue, setting] = useState<DepositContextProps["contextValue"]>(
    {},
  );

  const setContextValue = (id: string, value: any) => {
    setting((prev) => ({ ...prev, [id]: value }));
  };

  return (
    <ContextModule.Provider value={{ contextValue, setContextValue }}>
      {children}
    </ContextModule.Provider>
  );
};
