import { ContextProvider } from "../components/context/ContextProvider";
import ECommerce from "@/components/Dashboard/E-commerce";
import DefaultLayout from "@/components/Layouts/DefaultLayout";
import ApiClient from "./ApiClient";

export default function Home() {
  return (
    <ContextProvider>
      <ApiClient>
        <DefaultLayout>
          <ECommerce />
        </DefaultLayout>
      </ApiClient>
    </ContextProvider>
  );
}
