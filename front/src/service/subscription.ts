import axios, { AxiosError } from 'axios'

export default async function subscription(action: string, email: string): Promise<string> {
  try {
    const api_url = `${import.meta.env.VITE_API}/${action}`
    await axios.post(api_url, { email })
    return ''
  } catch (error: any) {               
    return `${(error as AxiosError).response?.data}`
  }
}
